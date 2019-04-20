package app.bxvip.com.myphone.ui.activity

import android.support.v4.view.ViewPager
import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.adapter.VideoPagerAdapter

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_video_player_jiecao.*
import cn.jzvd.JZVideoPlayerStandard
import com.itheima.player.model.VideoPlayBean
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.radioGroup


class JieCaoVideoPlayerActivity : BaseActivity() {
    override fun getlayoutId(): Int {
        return R.layout.activity_video_player_jiecao
    }

    override fun initData() {

        val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
//        videoplayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4", "饺子闭眼睛" , Jzvd.SCREEN_NORMAL)
//        Picasso.get().load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoplayer.thumbImageView)
       //在应用外请求时通过intent.data请求的
//        val data = intent.data
//        println("data=$data")
//        if (data == null) {
//            //获取传递的数据
//            val videoPlayBean = intent.getParcelableExtra<VideoPlayBean>("item")
//            //从应用内响应视频播放
//            videoplayer.setUp(videoPlayBean.url,
//                    JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, videoPlayBean.title)
//        } else {
//            if (data.toString().startsWith("http")) {
//                //应用外网络视频请求
//                //应用外响应
//                videoplayer.setUp(data.toString(),
//                        JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, data.toString())
//            } else {
//                //应用外的本地视频请求
//                //应用外响应
//                videoplayer.setUp(data.path,
//                        JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, data.toString())
//            }
//
//        }
        var data = intent .data
        println("=====$data")
        if (data == null){
            //从应用内响应
            videoplayer.setUp(videoPlayBean.url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,
                    videoPlayBean.title)
            Picasso.get().load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoplayer.thumbImageView)
        }else{
            if(data.toString().startsWith("http")){
                //网络视频请求
                videoplayer.setUp(data.toString(), JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,data.path)
                Picasso.get().load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoplayer.thumbImageView)
            }else{
                //本地视频请求
                //应用外响应
                videoplayer.setUp(data.path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,data.path)
                Picasso.get().load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoplayer.thumbImageView)
            }

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

    override fun initListener() {
        //适配viewpager
        viewPager.adapter = VideoPagerAdapter(supportFragmentManager)
        //radiogroup设置选中监听
        rg.setOnCheckedChangeListener { radioGroup, i ->
            when(i){
                R.id.rb1-> viewPager.setCurrentItem(0)
                R.id.rb2-> viewPager.setCurrentItem(1)
                R.id.rb3-> viewPager.setCurrentItem(2)
            }
        }
        //viewpager设置选中状态监听
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            //滑动回调
            }

            override fun onPageSelected(p0: Int) {
            //选中状态改变,当viewpager选中状态改变的时候切换RadioGroup的的选中条目
                when(p0){
                    0->rg.check(R.id.rb1)
                    1->rg.check(R.id.rb2)
                    2->rg.check(R.id.rb3)
                }
            }

            override fun onPageScrollStateChanged(p0: Int) {
                //滑动状态改变的回调

            }
        })
    }
}