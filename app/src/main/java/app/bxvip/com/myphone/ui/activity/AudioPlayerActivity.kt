package app.bxvip.com.myphone.ui.activity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.os.Binder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.inter.IService
import app.bxvip.com.myphone.services.AudioService
import com.itheima.player.model.AudioBean

class AudioPlayerActivity : BaseActivity() {
    val conn by lazy { AudioConnection() }
    override fun getlayoutId(): Int {
        return R.layout.activity_audio_player
    }

    override fun initData() {
        println("=================")
        val list  =  intent.getParcelableArrayListExtra<AudioBean>("list")
//        val position = intent.getIntExtra("position",-1)
//        val intent = Intent(this,AudioService::class.java)
//        intent.putExtra("list", list)
////        intent.putExtra("position", position)
        val intent = intent
        intent.setClass(this, AudioService::class.java)
        startService(intent)
        bindService(intent, conn, Context.BIND_AUTO_CREATE)

//        //播放音乐
//        val mediaPlayer = MediaPlayer()
//        mediaPlayer.setOnPreparedListener {
//            //开始播放
//            mediaPlayer.start()
//        }
//        mediaPlayer.setDataSource(list.get(position).data)
//        mediaPlayer.prepareAsync()
    }
    var iService:IService? = null
    inner class AudioConnection:ServiceConnection{
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
    }

}
