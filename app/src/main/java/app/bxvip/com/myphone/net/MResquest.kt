package app.bxvip.com.myphone.net

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.itheima.player.model.bean.HomeItemBean
import java.lang.reflect.ParameterizedType
open class MResquest<RESPONSE>(val url:String, val handler: ResponseHandler<RESPONSE>) {
   //解析网络请求的结果
    fun parseResult(result: String?): RESPONSE{
       var gson = Gson()
       //获取泛型的类型
       val type = (this.javaClass.genericSuperclass as ParameterizedType).getActualTypeArguments()[0]
       val fromJsonList = gson.fromJson<RESPONSE>(result, type)
       return fromJsonList
    }
}