package app.bxvip.com.myphone.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.drawable.AnimatedImageDrawable
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.SeekBar
import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.adapter.PopAdapter
import app.bxvip.com.myphone.inter.IService
import app.bxvip.com.myphone.services.AudioService
import app.bxvip.com.myphone.util.StringUtil
import app.bxvip.com.myphone.widget.PlayListpopuWindow
import com.itheima.player.model.AudioBean
import kotlinx.android.synthetic.main.activity_music_player_bottom.*
import kotlinx.android.synthetic.main.activity_music_player_middle.*
import kotlinx.android.synthetic.main.activity_music_player_top.*
import kotlinx.android.synthetic.main.activity_music_player_top.artist
import kotlinx.android.synthetic.main.item_mv.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class AudioPlayerActivity : BaseActivity(), View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemClickListener {
    //弹出的播放列表点击事件
    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //需要播放当前的歌曲
        iService?.playPosition(position)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        //进度改变回调
        // progress 改变后的进度
        //fromUser 为True表示的是手指拖动改变的进度
        if (fromUser){
            //更新播放进度
            iService?.seekTo(progress)
            //更新界面进度的显示
            updateProgress(progress)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        //手指触摸回调的信息

    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        //手指离开的回调信息
    }

    var audioBean: AudioBean? = null
    var drawable: AnimationDrawable? = null
    var duration: Int = 0
    var MSG_PROGRESS = 0
    val handler = object : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                MSG_PROGRESS -> startUpdateprogress()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.state -> uppdatePlayerState()
            R.id.mode -> updateMode()
            R.id.pre -> iService?.playPre()
            R.id.next -> iService?.playNext()
            R.id.playlist-> showPlayList()
        }
    }

    /**
     * 显示播放列表
     */
    private fun showPlayList() {
        val list  = iService?.getPlayList()
        list?.let {
            //创建adapter
            val adapter = PopAdapter(list)
            //获取底部的高度
            val bottom = audio_player_bottom.height
            val popWindow = PlayListpopuWindow(this,adapter, this,window)
            popWindow.showAsDropDown(audio_player_bottom,0,bottom)
        }

    }

    /**
     * 切换更新模式
     */
    private fun updateMode() {
        //修改service中的模式
        iService?.updateMode()
        //更改Activity中的界面图标
        updatePlayModeBtn()
    }

    /**
     * 根据当前模式修改播放图标
     */
    private fun updatePlayModeBtn() {
        //获取播放模式
        iService?.let{
            val modeI:Int = iService!!.getPlayMode()
            when(modeI){
                AudioService.MODE_ALL -> mode.setImageResource(R.drawable.selector_btn_playmode_order)
                AudioService.MODE_SINGLE -> mode.setImageResource(R.drawable.selector_btn_playmode_single)
                AudioService.MODE_RANDOM -> mode.setImageResource(R.drawable.selector_btn_playmode_random)
            }
        }
        //设置图标
    }

    /**
     * 接收
     */
    @Subscribe
    fun onEventMainThread(audioBean: AudioBean) {
        this.audioBean = audioBean
        //更细操作 歌手名称，歌曲名称
        audio_title.text = audioBean.display_name
        //歌手名称
        artist.text = audioBean.artist

        updatePlayerStateBtn()
        //处理动画的播放
        drawable = audio_anim.drawable as AnimationDrawable
        drawable?.start()
        //更新播放进度
        //获取总进度
        duration = iService?.getDuration()!!
        progress_sk.max = duration
        startUpdateprogress()
        //更新播放模式图标
        updatePlayModeBtn()
    }

    /**
     * 开始更新进度
     */
    private fun startUpdateprogress() {
        //获取当前进度
        var progress: Int? = iService?.getprogress()
        //更新进度数据
        progress?.let { updateProgress(it) }
        //定时获取进度
        handler.sendEmptyMessageDelayed(MSG_PROGRESS, 1000)

    }

    /**
     * 根据进度更新界面
     */
    private fun updateProgress(pro: Int) {
        progress.text = StringUtil.parseDuration(pro) + "/ " + StringUtil.parseDuration(duration)
        progress_sk.progress = pro
    }

    /**
     * 更新播放状态
     */
    private fun uppdatePlayerState() {
        //更新播放状态
        iService?.updatePlaterState()
        // 更新播放状态的图标
        updatePlayerStateBtn()
    }

    private fun updatePlayerStateBtn() {
        //获取当前的状态
        val isPlaying = iService?.isPlaying()
        println(isPlaying)
        //根据当前的状态设置图标
        isPlaying.let {
            if (isPlaying!!) {
                //播放
                state.setImageResource(R.drawable.selector_btn_audio_play)
                drawable?.start()
                handler.sendEmptyMessage(MSG_PROGRESS)
            } else {
                state.setImageResource(R.drawable.selector_btn_audio_pause)
                drawable?.stop()
                //暂停
                handler.removeMessages(MSG_PROGRESS)
            }
        }

    }

    val conn by lazy { AudioConnection() }
    override fun getlayoutId(): Int {
        return R.layout.activity_audio_player
    }

    override fun initListener() {
        state.setOnClickListener(this)
        back.setOnClickListener{finish()}
        progress_sk.setOnSeekBarChangeListener(this)
        mode.setOnClickListener(this)
        //上一曲和下一曲的点击事件
        pre.setOnClickListener(this)
        next.setOnClickListener(this)
        //播放列表点击事件
        playlist.setOnClickListener(this)
    }

    override fun initData() {
        //注册eventbus
        EventBus.getDefault().register(this)

        println("=================")
        val list = intent.getParcelableArrayListExtra<AudioBean>("list")
//        val position = intent.getIntExtra("position",-1)
//        val intent = Intent(this,AudioService::class.java)
//        intent.putExtra("list", list)
////        intent.putExtra("position", position)
        val intent = intent
        intent.setClass(this, AudioService::class.java)

        bindService(intent, conn, Context.BIND_AUTO_CREATE)
        startService(intent)
//        //播放音乐
//        val mediaPlayer = MediaPlayer()
//        mediaPlayer.setOnPreparedListener {
//            //开始播放
//            mediaPlayer.start()
//        }
//        mediaPlayer.setDataSource(list.get(position).data)
//        mediaPlayer.prepareAsync()
    }

    var iService: IService? = null

    inner class AudioConnection : ServiceConnection {
        /**
         * 意外断开的时候
         */
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        //service连接的时候
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iService = service as IService
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
        EventBus.getDefault().unregister(this)
        //清空handler发送的所有消息
        handler.removeCallbacksAndMessages(null)
        /**
         *
         * Remove any pending posts of callbacks and sent messages whose
         * <var>obj</var> is <var>token</var>.  If <var>token</var> is null,
         * all callbacks and messages will be removed.
        */

    }

}
