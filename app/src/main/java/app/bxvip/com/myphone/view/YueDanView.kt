package app.bxvip.com.myphone.view

import com.itheima.player.model.bean.YueDanBean


interface YueDanView {
    //获取数据失败
    abstract fun onError(message: String?)
    //初始化数据或者刷新数据成功
    abstract fun loadSuccess(response: YueDanBean?)
    //加载更多数据成功
    abstract fun loadMore(response: YueDanBean?)
}