package app.bxvip.com.myphone.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import app.bxvip.com.hbyinyin.base.BaseFragment
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.presenter.imple.MvPresenterImpl
import app.bxvip.com.myphone.view.MvView
import com.itheima.player.model.bean.MvAreaBean

class MvFragment : BaseFragment() ,MvView{
    val presenter by lazy { MvPresenterImpl(this) }
    override fun onError(message: String?) {
        myToast("加载数据失败")
    }

    override fun loadSuccess(response: List<MvAreaBean>?) {
        myToast("加载数据成功")
    }

    override fun loadMore(response: List<MvAreaBean>?) {

    }

    override fun initView(): View {
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