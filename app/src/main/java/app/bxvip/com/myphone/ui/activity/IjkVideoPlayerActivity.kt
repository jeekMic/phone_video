package app.bxvip.com.myphone.ui.activity

import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import com.itheima.player.model.VideoPlayBean
import kotlinx.android.synthetic.main.activity_video_player_ijk.*

class IjkVideoPlayerActivity: BaseActivity() {
    override fun getlayoutId(): Int {
        return R.layout.activity_video_player_ijk
    }

    override fun initData() {

        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        videoView.setVideoPath(videoPlayBean.url)
        //如果是异步加载的话，还没有加载成功就调用的话 会报错
        // videoView.start()
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //当窗口销毁的时候要释放资源
        videoView.stopPlayback()
    }
}