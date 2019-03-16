package app.bxvip.com.myphone.view

import com.itheima.player.model.bean.HomeItemBean

/**
 * 负责home界面的交互和presenter交互，这是一个接口，
 * Home界面实现了这个接口后，当presenter(提出者，推荐者，人命者，主持人)
 * 调用接口里面的方法时候，数据接口通过接口传递放前台界面
 */
interface HomeView {
    //获取数据失败
    abstract fun onError(message: String?)
    //初始化数据或者刷新数据成功
    abstract fun loadSuccess(fromJsonList: List<HomeItemBean>?)
    //加载更多数据成功
    abstract fun loadMore(fromJsonList: List<HomeItemBean>?)
}