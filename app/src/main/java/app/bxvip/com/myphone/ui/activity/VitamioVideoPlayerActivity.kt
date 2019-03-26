package app.bxvip.com.myphone.ui.activity

import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import com.itheima.player.model.VideoPlayBean
import io.vov.vitamio.Vitamio
import kotlinx.android.synthetic.main.activity_video_player_vitamor.*


class VitamioVideoPlayerActivity: BaseActivity() {
    override fun getlayoutId(): Int {
        return R.layout.activity_video_player_vitamor
    }

    override fun initData() {
        //初始化vitamio
        Vitamio.isInitialized(applicationContext)

        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
        videoView.setVideoPath(videoPlayBean.url)
        //如果是异步加载的话，还没有加载成功就调用的话 会报错
//        videoView.start()
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }
}