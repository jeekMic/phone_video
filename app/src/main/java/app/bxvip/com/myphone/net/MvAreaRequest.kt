package app.bxvip.com.myphone.net

import app.bxvip.com.myphone.util.URLProviderUtils
import com.itheima.player.model.bean.MvAreaBean

/**
 * MV区域数据请求类
 */
class MvAreaRequest(handler:ResponseHandler<List<MvAreaBean>>): MResquest<List<MvAreaBean>>(0,URLProviderUtils.mVareaUrl,handler)
{
}