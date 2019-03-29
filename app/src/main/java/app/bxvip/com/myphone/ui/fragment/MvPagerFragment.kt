package app.bxvip.com.myphone.ui.fragment

import app.bxvip.com.myphone.adapter.MvListAdapter
import app.bxvip.com.myphone.base.BaseListAdapter
import app.bxvip.com.myphone.base.BaseListFragment
import app.bxvip.com.myphone.base.BaseListPresenter
import app.bxvip.com.myphone.presenter.imple.MvListPresenterimple
import app.bxvip.com.myphone.ui.activity.*

import app.bxvip.com.myphone.view.MvListView
import app.bxvip.com.myphone.widget.MvItemView
import com.itheima.player.model.VideoPlayBean
import com.itheima.player.model.bean.MvPagerBean
import com.itheima.player.model.bean.VideosBean
import org.jetbrains.anko.support.v4.startActivity
import cn.jzvd.JZVideoPlayer.releaseAllVideos
import cn.jzvd.JZVideoPlayer.backPress




class MvPagerFragment: BaseListFragment<MvPagerBean, VideosBean, MvItemView>(), MvListView {
    var code :String? = null
    override fun init() {
        code = arguments?.getString("code")
    }
    override fun getSpecialAdapter(): BaseListAdapter<VideosBean, MvItemView> {
        return MvListAdapter()
    }

    override fun getSpecialpresenter(): BaseListPresenter {
        return  MvListPresenterimple(code!!, this)
    }

    override fun getList(response: MvPagerBean?): List<VideosBean> {
        //返回一个集合
        return response?.videos!!
    }

    override fun initListener() {
        super.initListener()
        //设置条目 点击事件监听操作
        adapter.setMyListener {
            //这里就是传递过来的数据 it表示传递过来的
            //这里就是传递过来的数据 it表示传递过来的
            myToast(it.hdUrl)
            val videoPlayBean = VideoPlayBean(it.id,it.title,it.url)
//            startActivity<VideoPlayerActivity>("item" to videoPlayBean)
//            startActivity<TexttureVideoPlayerActivity>("item" to videoPlayBean)
//            startActivity<VitamioVideoPlayerActivity>("item" to videoPlayBean)
            //VideoPlayBean 此处的videoPlayBean是经过序列化的，所以可以直接在流中进行传递
//            startActivity<IjkVideoPlayerActivity>("item" to videoPlayBean)
            startActivity<JieCaoVideoPlayerActivity>("item" to videoPlayBean)
            /**
             * 简要的分析下，在视频播放器中如果是SurfacerView实现类的子类，那么是能在视频列表中播放的，因为它是一个独立的View脱离框架
             * 如果是继承TextureView是可以像今日头条一样在视频列表中存在的
             * Vitamio是一个底层通过SurfacerView来加载
             * IjkVideoPlayerActivity 播放器是一款继承进度条标题等相关控件为一体的第三方框，底层实际上是继承了Textture，可以在列表中进行展示
             */
        }
    }

}