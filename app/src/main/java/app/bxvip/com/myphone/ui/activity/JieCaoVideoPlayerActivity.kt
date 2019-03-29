package app.bxvip.com.myphone.ui.activity

import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*
import cn.jzvd.JZVideoPlayerStandard
import com.itheima.player.model.VideoPlayBean
import org.jetbrains.anko.intentFor


class JieCaoVideoPlayerActivity : BaseActivity() {
    override fun getlayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }

    override fun initData() {

        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
//        videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4", "饺子闭眼睛" , Jzvd.SCREEN_NORMAL)
//        Picasso.get().load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoplayer.thumbImageView)
       //在应用外请求时通过intent.data请求的

        var data = intent .data
        myToast(data.toString())
        if (data == null){
            //从应用内响应
            videoplayer.setUp(videoPlayBean.url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                    videoPlayBean.title)
            Picasso.get().load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoplayer.thumbImageView)
        }else{

            //应用外响应
            videoplayer.setUp(data.path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,data.path)
            Picasso.get().load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoplayer.thumbImageView)
        }


    }
    override fun onBackPressed() {
        if (JZVideoPlayerStandard.backPress()) {
            return
        }
        super.onBackPressed()
    }

     override fun onPause() {
        super.onPause()
        JZVideoPlayerStandard.releaseAllVideos()
    }
}