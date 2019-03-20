package app.bxvip.com.myphone.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import app.bxvip.com.hbyinyin.base.BaseFragment
import app.bxvip.com.myphone.adapter.MvListAdapter
import app.bxvip.com.myphone.base.BaseListAdapter
import app.bxvip.com.myphone.base.BaseListFragment
import app.bxvip.com.myphone.base.BaseListPresenter
import app.bxvip.com.myphone.presenter.imple.MvListPresenterimple
import app.bxvip.com.myphone.presenter.interf.MvListPresenter
import app.bxvip.com.myphone.view.MvListView
import app.bxvip.com.myphone.widget.MvItemView
import com.itheima.player.model.bean.MvPagerBean
import com.itheima.player.model.bean.VideosBean


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
        return response?.videos!!
    }

}