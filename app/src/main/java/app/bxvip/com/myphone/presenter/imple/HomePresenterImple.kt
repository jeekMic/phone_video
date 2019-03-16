package app.bxvip.com.myphone.presenter.imple

import app.bxvip.com.myphone.base.BaseListPresenter
import app.bxvip.com.myphone.net.HomeRequest
import app.bxvip.com.myphone.net.NetManager
import app.bxvip.com.myphone.net.ResponseHandler
import app.bxvip.com.myphone.presenter.interf.HomePresenter
import app.bxvip.com.myphone.util.ThreadUtil
import app.bxvip.com.myphone.util.URLProviderUtils
import app.bxvip.com.myphone.view.HomeView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.*
import java.io.IOException

class HomePresenterImple(var homeView: HomeView?) :HomePresenter,ResponseHandler<List<HomeItemBean>>{
    /**
     * 解绑
     */
   override fun destoryView(){
       if (homeView!=null){
           homeView = null
       }
   }


    override fun onError(type:Int,msg: String?) {
        homeView?.onError(msg)
    }

    override fun onSuccess(type:Int,result: List<HomeItemBean>?) {
        when(type){
            BaseListPresenter.TYPE_INIT_OR_REFRESH -> homeView?.loadSuccess(result)
            BaseListPresenter.TYPE_LOAD_MORE -> homeView?.loadMore(result)
        }
    }
//    override fun onError(msg: String?) {
//        homeView.onError(msg)
//    }
//
//    override fun onSuccess(result: List<HomeItemBean>?) {
//        homeView.loadSuccess(result)
//    }
    /**
     * 初始化数据或者刷新数据
     */
    override fun loadDatas() {
        //定义request
        HomeRequest( BaseListPresenter.TYPE_INIT_OR_REFRESH,0, this).execute()
//        NetManager.manager.sendRequest(request)

    }

    override fun loadMore(i: Int) {
        //定义request
        HomeRequest( BaseListPresenter.TYPE_LOAD_MORE,i, this).execute()
//        NetManager.manager.sendRequest(request)

    }

}