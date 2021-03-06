package app.bxvip.com.myphone.base

import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import app.bxvip.com.hbyinyin.base.BaseFragment
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.adapter.HomeAdapter
import app.bxvip.com.myphone.presenter.imple.HomePresenterImple
import app.bxvip.com.myphone.view.HomeView
import com.itheima.player.model.bean.HomeItemBean
import com.itheima.player.model.bean.YueDanBean
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.Response
import org.jetbrains.anko.support.v4.toast

/**
 * 基类抽取
 * 所有具有上啦刷新，下拉加载的基类
 * HomeView -> BaseView
 * adapter -> BaseListAdapter
 * Presenter -> BaseListPresenter
 */
abstract  class BaseListFragment<RESPONSE,ITEMBEAN,ITEMVIEW:View> : BaseFragment() , BaseView<RESPONSE> {
    //惰性加载适配器，只会加载一次
    val adapter by lazy { getSpecialAdapter() }
    val presenter by lazy { getSpecialpresenter() }
    //加载视图给fragment
    override fun initView(): View {
        return  View.inflate(context, R.layout.fragment_home,null)
    }
    abstract  fun getSpecialAdapter():BaseListAdapter<ITEMBEAN,ITEMVIEW>
    abstract fun getSpecialpresenter():BaseListPresenter
    //监听事件，主要是设置RecycleView的相关事件信息
    override fun initListener() {
        //线性的布局 recycleView
        recycleView.layoutManager = LinearLayoutManager(context)
        //设置适配器，适配器在上面进行了惰性加载
        recycleView.adapter = adapter
        //初始化刷新控件，每转一圈还一种颜色
        refreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW, Color.GREEN)
        //设置监听 这是 lambda的写法，里面实际上是重写了 onRefresh这个方法，当调用动画后就会调用这个监听
        refreshLayout.setOnRefreshListener { presenter.loadDatas() }
        //监听列表滑动
        recycleView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    //是否最后一条显示了
                    val layoutManager = recyclerView.layoutManager
                    if (layoutManager is LinearLayoutManager){
                        val manger = layoutManager
                        val lastPosition = manger.findLastVisibleItemPosition()
                        if (lastPosition==adapter.itemCount-1){
                            //已经是最后1条数据了
                            presenter.loadMore(adapter.itemCount-1)
                        }
                    }
                }
            }
        })
    }

    override fun initData() {
        presenter.loadDatas()
    }


    override fun onError(message: String?) {
        toast("加载数据失败")
    }

    override fun loadSuccess(response:RESPONSE?) {
        if(refreshLayout!=null){
            refreshLayout.isRefreshing = false
        }
        adapter.updateList(getList(response))
    }



    override fun loadMore(response:RESPONSE?) {
        adapter.loadMoreData(getList(response))
    }

    /**
     * 返回结果中获取当前列表集合的方法
     */
    abstract fun getList(response: RESPONSE?): List<ITEMBEAN>
}