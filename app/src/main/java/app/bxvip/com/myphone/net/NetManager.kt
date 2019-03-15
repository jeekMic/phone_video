package app.bxvip.com.myphone.net

import app.bxvip.com.myphone.util.ThreadUtil
import app.bxvip.com.myphone.util.URLProviderUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import okhttp3.*
import java.io.IOException

//设置单例模式
class NetManager private constructor(){
    val client by lazy { OkHttpClient() }
    /**
     * 伴生对象类似java中的static关键字引导的静态方法属性，可以直接用类名加载，配合惰性加载构成了单例模式
     */
    companion object {
        val manager by lazy {
            NetManager()
        }
    }

    /**
     * 发送网络请求
     */
     fun <RESPONSE> sendRequest(req:MResquest<RESPONSE>){
//        var path = URLProviderUtils.getHomeUrl(0,20)

        val request = Request.Builder()
                .url(req.url)
                .get()
                .build()
        //这里的object表示的是匿名内部类的对象
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //回调到View层
//                homeView.onError(e.message)
                ThreadUtil.runOnMainThread(object:Runnable{
                    override fun run() {
                        req.handler.onError(e.message)
                    }
                })
            }

            override fun onResponse(call: Call, response: Response) {

                val result = response.body()?.string()
//                var gson = Gson()
//                val fromJsonList = gson.fromJson<List<HomeItemBean>>(result, object : TypeToken<List<HomeItemBean>>() {}.type)

                val parseResult = req.parseResult(result)
                //刷新列表
                ThreadUtil.runOnMainThread(object:Runnable{
                    override fun run() {
                        req.handler.onSuccess(parseResult)
                    }
                })
            }
        })
    }
}
