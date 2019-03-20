package app.bxvip.com.myphone.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import app.bxvip.com.hbyinyin.base.BaseFragment
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.adapter.MvPagerAdapter
import app.bxvip.com.myphone.presenter.imple.MvPresenterImpl
import app.bxvip.com.myphone.view.MvView
import com.itheima.player.model.bean.MvAreaBean
import kotlinx.android.synthetic.main.fragment_mv.*

/**
  * 实现 MvView接收结果的回调
 * 同时，这里也经过惰性加载了一个 presenter
 * 说明MVP模式 View和 presenter视图双向绑定
 */
class MvFragment : BaseFragment() ,MvView{
    val presenter by lazy { MvPresenterImpl(this) }


    override fun onError(message: String?) {
        myToast("加载数据失败")
    }

    override fun loadSuccess(response: List<MvAreaBean>?) {
        myToast("加载数据成功")
        //在fragment管理fragment需要使用 childFragmentManager
        val adapter = context?.let {
            MvPagerAdapter(it,response, childFragmentManager)
        }
        viewPager.adapter = adapter
        //tablelayout和viewpager关联起来
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun loadMore(response: List<MvAreaBean>?) {

    }

    /**
     * 下面三个方法是BaseFragment中的方法，主要是初始化数据
     */
    override fun initView(): View {
        //加载布局并放到当前ViewGroup中
        return  View.inflate(context, R.layout.fragment_mv,null)
    }

    override fun initListener() {
        super.initListener()
    }

    override fun initData() {
       //加载初始化的数据
        presenter.loadDatas()
    }
}