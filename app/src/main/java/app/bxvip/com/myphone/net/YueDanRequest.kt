package app.bxvip.com.myphone.net

import app.bxvip.com.myphone.adapter.YueDanAdapter
import app.bxvip.com.myphone.util.URLProviderUtils
import com.itheima.player.model.bean.YueDanBean

class YueDanRequest(type:Int,offset:Int,handler:ResponseHandler<YueDanBean>):
        MResquest<YueDanBean>(type,URLProviderUtils.getYueDanUrl(offset,20), handler){
}