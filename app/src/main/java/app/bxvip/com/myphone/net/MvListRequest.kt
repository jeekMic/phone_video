package app.bxvip.com.myphone.net

import app.bxvip.com.myphone.util.URLProviderUtils
import com.itheima.player.model.bean.MvPagerBean

/**
 * mv每一个数据的网络请求
 */
class MvListRequest(type: Int, code: String, offset: Int, handler: ResponseHandler<MvPagerBean>) :
        MResquest<MvPagerBean>(type, URLProviderUtils.getMVListUrl(code, offset, 20), handler) {
}