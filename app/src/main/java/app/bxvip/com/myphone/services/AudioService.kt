package app.bxvip.com.myphone.services

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.inter.IService
import app.bxvip.com.myphone.ui.activity.AudioPlayerActivity
import com.itheima.player.model.AudioBean
import org.greenrobot.eventbus.EventBus
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random
import kotlin.random.nextInt

class AudioService:Service() {
    val FROM_PRE = 1
    val FROM_NEXT = 2
    val FROM_STATE = 3
    val FROM_CONTENT = 4
    var list:ArrayList<AudioBean>? = null
    var mediaPlayer:MediaPlayer? =null
    var position:Int? = -2 //正在播放的position
    val bindder by lazy { AudioBinder() }
    val sp  by lazy { getSharedPreferences("conf", Context.MODE_PRIVATE) }
    var notification:Notification? = null
    companion object{
        val MODE_ALL = 1
        val MODE_SINGLE = 2
        val MODE_RANDOM = 3
    }
    var mode = MODE_ALL
    override fun onCreate() {
        super.onCreate()
        mode = sp.getInt("mode", 1)


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val result = intent?.getIntExtra("from",0)
        println("onStartCommand==========${result}")
        when(result){
            FROM_PRE->{
//                上一曲
                bindder.playPre()
            }
            FROM_NEXT->{
//                下一曲
                bindder.playNext()
            }
            FROM_STATE->{
//                暂停
                bindder.updatePlaterState()

            }
            FROM_CONTENT->{
//                返回播放的activity
                notifyUpdateUi()
            }
            else->{

                //进程强制杀死的时候，这里还会调用一次
                val pos = intent?.getIntExtra("position", -1)?:-1
                if (pos!= position){
                    position = pos
                    //这里是传过来的intent
                    list = intent?.getParcelableArrayListExtra<AudioBean>("list")
                    //开始播放音乐
                    bindder.playItem()
                }else{
                    notifyUpdateUi()
                }
            }
        }

//        START_STICKY 意外杀死会尝试重启 但是不会传递intent 所以为空
//        START_NOT_STICKY 非粘性的，强制杀死后不会重新启动
//        START_REDELIVER_INTENT 强制杀死后会重新启动，并且让会传递之前的intent
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    override fun onBind(intent: Intent?): IBinder? {
        return bindder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        println("onUnbind")
        return super.onUnbind(intent)
    }

    inner class AudioBinder: Binder(),IService, MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
        var manager:NotificationManager?= null
        /**
         * 播放当前位置的歌曲
         */
        override fun playPosition(position: Int) {
            this@AudioService.position = position
            playItem()
        }

        /**
         * 获取播放集合
         */
        override fun getPlayList(): List<AudioBean>? {
            return list
        }

        /**
         * 播放上一曲
         */
        override fun playPre() {
            //获取要播放歌曲的position
        list?.let {

            when(mode){
                MODE_RANDOM-> position = Random.nextInt(it.size-1)
                else->{
                    if(position==0){
                        position = it.size-1
                    }else{
                        position = position?.minus(1)
                    }
                }
            }

        }
            playItem()
        }

        /**
         * 播放下一句
         */
        override fun playNext() {
            println("onStartCommand==============下一首")
            list?.let {
                when(mode){
                    MODE_RANDOM-> position = Random.nextInt(it.size-1)
                    else->{
                        if(position==0){
                            position = it.size-1
                        }else{
                            position = position?.minus(1)
                        }
                    }
                }

            }
            playItem()
        }

        /**
         * 获取播放模式
         */
        override fun getPlayMode(): Int {
            return mode
        }

        /**
         * 修改切换模式
         */
        override fun updateMode() {
            when(mode){
                MODE_ALL-> mode = MODE_SINGLE
                MODE_SINGLE-> mode = MODE_RANDOM
                MODE_RANDOM-> mode = MODE_ALL
            }
            sp.edit().putInt("mode", mode).apply()
        }

        override fun onCompletion(mp: MediaPlayer?) {
            //歌曲播放完成后执行的回调 自动播放下一曲
            autoPlaynext()
        }

        /**
         * 开启
         */
        fun start():Unit{
            println("===service===start")
            mediaPlayer?.start()
            notification?.contentView?.setImageViewResource(R.id.state_m,R.mipmap.btn_audio_play_normal)
            notifyUpdateUi()
            manager?.notify(1,notification)
        }

        /**
         * 关闭
         */
        fun pause():Unit{
            println("===service===pause")
            mediaPlayer?.pause()
            notification?.contentView?.setImageViewResource(R.id.state_m,R.mipmap.btn_audio_pause_normal)
            notifyUpdateUi()
            manager?.notify(1,notification)
        }
        /**
         * 跳转到当前进度
         */
        override fun seekTo(progress: Int) {
            mediaPlayer?.seekTo(progress)
        }

        /**
         * 获取当前的进度
         */
        override fun getprogress(): Int {
            return mediaPlayer?.currentPosition?:0
        }

        /**
         * 获取总进度
         */
        override fun getDuration(): Int {
            return mediaPlayer?.duration?:0
        }

        override fun isPlaying(): Boolean? {
            return mediaPlayer?.isPlaying
        }

        override fun updatePlaterState() {
            println("restart")
            //获取当前播放状态
            val isPlaying = isPlaying()
            isPlaying?.let{
                if(isPlaying){
                    //播放->暂停
//                    mediaPlayer?.pause()
                    pause()

                }else{
                    //暂停-> 播放
//                    mediaPlayer?.start()
                    start()
                }
            }
        }



        override fun onPrepared(mp: MediaPlayer?) {
                mediaPlayer?.start()
                //通知界面更新
                notifyUpdateUi()
                //显示通知状态栏
               showNotification()
        }

        private fun showNotification() {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager?.notify(1, getNotification())
        }

        private fun getNotification(): Notification? {
            notification =  NotificationCompat.Builder(this@AudioService)
                    .setTicker("正在播放歌曲${position?.let { list?.get(it)?.display_name }}")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setCustomContentView(getRemoteView())
                    .setShowWhen(true)
                    .setWhen(System.currentTimeMillis())
                    .setOngoing(true)//设置不能滑动删除
                    .setContentIntent(getPendingIntent())
                    .build()
            return notification

        }

        private fun getPendingIntent(): PendingIntent? {
            println("onStartCommand==点击了主体")
            val intentA = Intent(this@AudioService,AudioPlayerActivity::class.java)//点击主体进入当前界面中
            intentA.putExtra("from",FROM_CONTENT)
            val pendingIntent = PendingIntent.getActivity(this@AudioService,1,intentA, FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        private fun getRemoteView(): RemoteViews? {
            var remoteViews = RemoteViews(packageName,R.layout.notificationlayout)
            remoteViews.setTextViewText(R.id.title_m,"${position?.let { list?.get(it)?.display_name }}")
            remoteViews.setTextViewText(R.id.artist_m,"${position?.let { list?.get(it)?.artist }}")
            remoteViews.setOnClickPendingIntent(R.id.pre_m,getPreIntent())
            remoteViews.setOnClickPendingIntent(R.id.state_m,getStateIntent())
            remoteViews.setOnClickPendingIntent(R.id.next_m,getNextIntent())
            return remoteViews
        }
        /**
         * 用户点击了上一曲的按钮
         */
        private fun getPreIntent(): PendingIntent? {
            println("onStartCommand==上一曲")
            val intent = Intent(this@AudioService,AudioService::class.java)
            intent.putExtra("from",FROM_PRE)
            var pendingIntent:PendingIntent = PendingIntent.getService(this@AudioService,102, intent, FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 用户点击了暂停的按钮
         */
        private fun getStateIntent(): PendingIntent?{
            println("onStartCommand==暂停")
            val intent = Intent(this@AudioService, AudioService::class.java)
            intent.putExtra("from",FROM_STATE)
            val pendingIntent: PendingIntent = PendingIntent.getService(this@AudioService,103,intent, FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        /**
         * 用户点击了下一曲的按钮
         */
        private fun getNextIntent():PendingIntent? {
            println("onStartCommand==下一曲")
            val intent = Intent(this@AudioService, AudioService::class.java)
            intent.putExtra("from",FROM_NEXT)
            val pendingIntent:PendingIntent = PendingIntent.getService(this@AudioService,104,intent, FLAG_UPDATE_CURRENT)
            return pendingIntent
        }

        fun playItem(){
            //如果mediaPlayer已经存在就销毁
            if (mediaPlayer !=null){
                mediaPlayer?.reset()
                mediaPlayer?.release()
                mediaPlayer = null
            }
            //播放音乐
                mediaPlayer = MediaPlayer()
//            mediaPlayer.setOnPreparedListener {
//                //开始播放
//                mediaPlayer.start()
//            }
            mediaPlayer?.setOnPreparedListener(this)
            mediaPlayer?.setOnCompletionListener(this)
            mediaPlayer?.setDataSource(position?.let { list?.get(it)?.data })
            mediaPlayer?.prepareAsync()
        }
        /**
         * 根据播放模式自动播放下一曲
         */
        private fun autoPlaynext() {
            when(mode){
                MODE_ALL-> {
                    list?.let {
                        position = (position?.plus(1))?.rem(list!!.size)
                    }
                }
//            MODE_SINGLE->
                MODE_RANDOM-> list?.let{  position = Random.nextInt(list!!.size) }
            }
            playItem()
        }

    }


    /**
     * 通知界面更新
     */
    fun notifyUpdateUi() {
        //发送端
        EventBus.getDefault().post(position?.let { list?.get(it) })
    }
}