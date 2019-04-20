package app.bxvip.com.myphone.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import app.bxvip.com.myphone.inter.IService
import com.itheima.player.model.AudioBean

class AudioService:Service() {
    var list:ArrayList<AudioBean>? = null
    var mediaPlayer:MediaPlayer? =null
    var position:Int? = 0
    val bindder by lazy { AudioBinder() }
    override fun onCreate() {
        super.onCreate()

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("onStartCommand==========")
        //进程强制杀死的时候，这里还会调用一次
       //这里是传过来的intent
        list = intent?.getParcelableArrayListExtra<AudioBean>("list")
        position = intent?.getIntExtra("position", -1)?:-1
        //开始播放音乐
        bindder.playItem()
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
    fun sayHello(){
        println("print say hello from services")
    }
    inner class MyBinder:Binder(), IService {
        override fun callSayHello() {
            sayHello()
        }
    }
    inner class AudioBinder: Binder(),IService, MediaPlayer.OnPreparedListener {
        override fun callSayHello() {

        }

        override fun onPrepared(mp: MediaPlayer?) {
                mediaPlayer?.start()
        }

        fun playItem(){
            //播放音乐
                mediaPlayer = MediaPlayer()
//            mediaPlayer.setOnPreparedListener {
//                //开始播放
//                mediaPlayer.start()
//            }
            mediaPlayer?.setOnPreparedListener(this)
            mediaPlayer?.setDataSource(position?.let { list?.get(it)?.data })
            mediaPlayer?.prepareAsync()
        }
    }
}