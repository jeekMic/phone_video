package app.bxvip.com.myphone.net

import app.bxvip.com.myphone.util.URLProviderUtils
import com.itheima.player.model.bean.HomeItemBean
import java.net.URLClassLoader

/**
 * HomeRequest 需要两个参数
 * para1 是请求的其实，如果是加载更多，可以拿到当前的count 将cout-i传进去
 * para2 第二个参数是一个接口
 * 本类继承了MResquest， MResquest主要是将得到的数据进行解析，返回的类型是List<HomeItemBean>
 */
class HomeRequest(val types:Int,offset:Int, handler:ResponseHandler<List<HomeItemBean>>):
        MResquest<List<HomeItemBean>>(types,URLProviderUtils.getHomeUrl(offset,20),handler)
{
}