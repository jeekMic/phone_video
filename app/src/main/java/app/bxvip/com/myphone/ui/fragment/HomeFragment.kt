package app.bxvip.com.myphone.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import app.bxvip.com.hbyinyin.base.BaseFragment
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.adapter.HomeAdapter
import app.bxvip.com.myphone.base.BaseListAdapter
import app.bxvip.com.myphone.base.BaseListFragment
import app.bxvip.com.myphone.base.BaseListPresenter
import app.bxvip.com.myphone.presenter.imple.HomePresenterImple

import app.bxvip.com.myphone.view.HomeView
import app.bxvip.com.myphone.widget.HomeItemView
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.toast

//继承BaseFragment，封装一些公用的方法和属性
//实现HomeView 接收回调信息
class HomeFragment : BaseListFragment<List<HomeItemBean>,HomeItemBean,HomeItemView>() {
    override fun getSpecialAdapter(): BaseListAdapter<HomeItemBean, HomeItemView> {
        return HomeAdapter()
    }

    override fun getSpecialpresenter(): BaseListPresenter {
        return HomePresenterImple(this)
    }

    override fun getList(response: List<HomeItemBean>?): List<HomeItemBean> {
        return response!!
    }

}