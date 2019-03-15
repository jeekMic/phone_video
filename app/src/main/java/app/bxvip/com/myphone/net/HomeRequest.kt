package app.bxvip.com.myphone.net

import app.bxvip.com.myphone.util.URLProviderUtils
import com.itheima.player.model.bean.HomeItemBean
import java.net.URLClassLoader

class HomeRequest(offset:Int, handler:ResponseHandler<List<HomeItemBean>>): MResquest<List<HomeItemBean>>(URLProviderUtils.getHomeUrl(offset,20),handler) {
}