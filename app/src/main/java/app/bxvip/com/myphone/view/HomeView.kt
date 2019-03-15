package app.bxvip.com.myphone.view

import com.itheima.player.model.bean.HomeItemBean

/**
 * 负责home界面的交互和presenter交互
 */
interface HomeView {
    //获取数据失败
    abstract fun onError(message: String?)
    //初始化数据或者刷新数据成功
    abstract fun loadSuccess(fromJsonList: List<HomeItemBean>?)
    //加载更多数据成功
    abstract fun loadMore(fromJsonList: List<HomeItemBean>?)
}