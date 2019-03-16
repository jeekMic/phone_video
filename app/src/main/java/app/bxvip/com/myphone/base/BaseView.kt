package app.bxvip.com.myphone.base

import com.itheima.player.model.bean.HomeItemBean

interface BaseView<RESPONSE>{
    //获取数据失败
    abstract fun onError(message: String?)
    //初始化数据或者刷新数据成功
    abstract fun loadSuccess(fromJsonList: RESPONSE?)
    //加载更多数据成功
    abstract fun loadMore(fromJsonList: RESPONSE?)
}