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
import app.bxvip.com.myphone.presenter.imple.YueDanpresenterImpl
import app.bxvip.com.myphone.view.YueDanView
import com.itheima.player.model.bean.YueDanBean
import kotlinx.android.synthetic.main.fragment_list.*

class YueDanFragment : BaseFragment(), YueDanView {


    override fun onError(message: String?) {
        refreshLayout.isRefreshing = false
        myToast("加载数据失败")
    }

    override fun loadSuccess(response: YueDanBean?) {
        refreshLayout.isRefreshing = false
        //刷新adapter
        adapter.updateList(response?.playLists)
        myToast("加载数据成功")
    }

    override fun loadMore(response: YueDanBean?) {
        adapter.loadMoreData(response?.playLists)
    }

    val adapter by lazy { YueDanAdapter() }
    val presenter by lazy { YueDanpresenterImpl(this) }
    //为当前的fragment加载视图
    override fun initView(): View {
        return  View.inflate(context, R.layout.fragment_list, null)
    }

    override fun initListener() {
        //给当前的recycleView 设置为线性布局
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter
        //设置加载进度条的颜色
        refreshLayout.setColorSchemeColors(Color.RED,Color.YELLOW,Color.GREEN)
        //监听刷新控件
        refreshLayout.setOnRefreshListener {
            presenter.loadDatas()
        }
        //监听列表的滑动
        recycleView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState==RecyclerView.SCROLL_STATE_IDLE){
                    //空闲状态，判断最后的可见条目是不是数据的最后一条
                    val layoutManager = recyclerView?.layoutManager
                    if (!(layoutManager is LinearLayoutManager)) return //如果是其他布局类型就没有这个属性
                    //显示列表
                    val lastPos = layoutManager.findFirstVisibleItemPosition()
                    if (lastPos ==adapter.itemCount-1){
                        //加载更多
                        presenter.loadMore(adapter.itemCount-1)
                    }
                }
            }
        })
    }

    override fun initData() {
        presenter.loadDatas()
    }
}