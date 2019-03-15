package app.bxvip.com.myphone.presenter.imple

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

class HomePresenterImple(var homeView: HomeView) :HomePresenter{
    /**
     * 初始化数据或者刷新数据
     */
    override fun loadDatas() {
        //定义request
        val request = HomeRequest(0, object : ResponseHandler<List<HomeItemBean>>{
            override fun onError(msg: String?) {
                homeView.onError(msg)
            }

            override fun onSuccess(result: List<HomeItemBean>?) {
                homeView.loadSuccess(result)
            }
        })
        NetManager.manager.sendRequest(request)



//        var path = URLProviderUtils.getHomeUrl(0,20)
//        println(path)
//        var client = OkHttpClient()
//        val request = Request.Builder()
//                .url(path)
//                .get()
//                .build()
//        //这里的object表示的是匿名内部类的对象
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                //回调到View层
//                homeView.onError(e.message)
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//
//
//                val result = response.body()?.string()
//                var gson = Gson()
//                val fromJsonList = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
//                //刷新列表
//                ThreadUtil.runOnMainThread(object:Runnable{
//                    override fun run() {
//                        homeView.loadSuccess(fromJsonList)
//                    }
//                })
//            }
//        })

    }

    override fun loadMore(offset: Int) {
        //定义request
        val request = HomeRequest(offset, object : ResponseHandler<List<HomeItemBean>>{
            override fun onError(msg: String?) {
                homeView.onError(msg)
            }

            override fun onSuccess(result: List<HomeItemBean>?) {
                homeView.loadMore(result)
            }
        })
        NetManager.manager.sendRequest(request)

//        var path = URLProviderUtils.getHomeUrl(offset,20)
//        println(path)
//        var client = OkHttpClient()
//        val request = Request.Builder()
//                .url(path)
//                .get()
//                .build()
//        //这里的object表示的是匿名内部类的对象
//        client.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                homeView.onError(e.message)
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                ThreadUtil.runOnMainThread(object: Runnable{
//                    override fun run() {
//                        //隐藏刷新控件
//
//                    }
//                })
//
//                val result = response.body()?.string()
//                var gson = Gson()
//                val fromJsonList = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)
//                //刷新列表
//                ThreadUtil.runOnMainThread(object:Runnable{
//                    override fun run() {
//                        homeView.loadMore(fromJsonList)
//                    }
//                })
//            }
//        })
    }

}