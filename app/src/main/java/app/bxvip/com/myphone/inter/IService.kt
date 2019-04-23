package app.bxvip.com.myphone.inter

import com.itheima.player.model.AudioBean

interface IService {
    fun updatePlaterState()
    fun isPlaying():Boolean?
    abstract fun getDuration(): Int
    abstract fun getprogress(): Int
    abstract fun seekTo(progress: Int)
    abstract fun updateMode()
    abstract fun getPlayMode(): Int
    fun playPre()
    fun playNext()
    abstract fun getPlayList(): List<AudioBean>?
    abstract fun playPosition(position: Int)
}