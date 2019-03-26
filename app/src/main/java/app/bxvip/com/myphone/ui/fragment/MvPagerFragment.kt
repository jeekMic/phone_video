package app.bxvip.com.myphone.ui.fragment

import app.bxvip.com.myphone.adapter.MvListAdapter
import app.bxvip.com.myphone.base.BaseListAdapter
import app.bxvip.com.myphone.base.BaseListFragment
import app.bxvip.com.myphone.base.BaseListPresenter
import app.bxvip.com.myphone.presenter.imple.MvListPresenterimple
import app.bxvip.com.myphone.ui.activity.IjkVideoPlayerActivity
import app.bxvip.com.myphone.ui.activity.TexttureVideoPlayerActivity

import app.bxvip.com.myphone.ui.activity.VideoPlayerActivity
import app.bxvip.com.myphone.ui.activity.VitamioVideoPlayerActivity
import app.bxvip.com.myphone.view.MvListView
import app.bxvip.com.myphone.widget.MvItemView
import com.itheima.player.model.VideoPlayBean
import com.itheima.player.model.bean.MvPagerBean
import com.itheima.player.model.bean.VideosBean
import org.jetbrains.anko.support.v4.startActivity


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
            myToast(it.hdUrl)
            val videoPlayBean = VideoPlayBean(it.id,it.title,it.url)
//            startActivity<VideoPlayerActivity>("item" to videoPlayBean)
//            startActivity<TexttureVideoPlayerActivity>("item" to videoPlayBean)
//            startActivity<VitamioVideoPlayerActivity>("item" to videoPlayBean)
            startActivity<IjkVideoPlayerActivity>("item" to videoPlayBean)
        }
    }
}