package app.bxvip.com.myphone.ui.fragment

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import app.bxvip.com.hbyinyin.base.BaseFragment
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.adapter.YueDanAdapter
import app.bxvip.com.myphone.base.BaseListAdapter
import app.bxvip.com.myphone.base.BaseListFragment
import app.bxvip.com.myphone.base.BaseListPresenter
import app.bxvip.com.myphone.presenter.imple.YueDanpresenterImpl
import app.bxvip.com.myphone.view.YueDanView
import app.bxvip.com.myphone.widget.YueDanItemView
import com.itheima.player.model.bean.YueDanBean
import kotlinx.android.synthetic.main.fragment_list.*

class YueDanFragment : BaseListFragment<YueDanBean, YueDanBean.PlayListsBean, YueDanItemView>() {
    override fun getSpecialAdapter(): BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView> {
        return YueDanAdapter()
    }

    override fun getSpecialpresenter(): BaseListPresenter {
        return YueDanpresenterImpl(this)
    }

    override fun getList(response: YueDanBean?): List<YueDanBean.PlayListsBean>{
        return response?.playLists!!
    }

}