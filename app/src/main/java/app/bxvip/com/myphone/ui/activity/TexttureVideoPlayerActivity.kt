package app.bxvip.com.myphone.ui.activity

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import com.itheima.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player.*
import kotlinx.android.synthetic.main.activity_video_player_texture.*

class TexttureVideoPlayerActivity: BaseActivity(), TextureView.SurfaceTextureListener {
    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
        //view大小发生变化的时候
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
        //视图更新时调用
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        //关闭Mediaplay
        mediaPlay?.let {
            mediaPlay.stop()
            mediaPlay.release()
        }
        // 视图销毁时调用
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        videoPlayBean?.let {
            //视图可用时候
            mediaPlay.setDataSource(videoPlayBean?.url)
            mediaPlay.setSurface(Surface(surface)) //设置视图播放画面
            mediaPlay.prepareAsync()
            mediaPlay.setOnPreparedListener {
                mediaPlay.start()
                //使用Texture是可以旋转view的之前的surface不能这样操作
                //testureView.rotation = 100f
            }
        }

    }

    override fun getlayoutId(): Int {
        return R.layout.activity_video_player_texture
    }
    var videoPlayBean:VideoPlayBean? = null
    val mediaPlay by lazy { MediaPlayer() }
    override fun initData() {

      videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
//        videoView.setVideoPath(videoPlayBean.url)
//        //如果是异步加载的话，还没有加载成功就调用的话 会报错
////        videoView.start()
//        videoView.setOnPreparedListener {
//            videoView.start()
//        }
        testureView.surfaceTextureListener = this
    }
}