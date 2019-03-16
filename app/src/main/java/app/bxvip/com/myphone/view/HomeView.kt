package app.bxvip.com.myphone.view

import app.bxvip.com.myphone.base.BaseView
import com.itheima.player.model.bean.HomeItemBean
import com.itheima.player.model.bean.YueDanBean

/**
 * 负责home界面的交互和presenter交互，这是一个接口，
 * Home界面实现了这个接口后，当presenter(提出者，推荐者，人命者，主持人)
 * 调用接口里面的方法时候，数据接口通过接口传递放前台界面
 */
interface HomeView : BaseView<List<HomeItemBean>> {

}