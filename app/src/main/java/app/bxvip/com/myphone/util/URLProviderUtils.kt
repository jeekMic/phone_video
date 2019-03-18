package app.bxvip.com.myphone.util

import android.util.Log
import app.bxvip.com.myphone.util.URLProviderUtils.phoneModel
import app.bxvip.com.myphone.util.URLProviderUtils.systemVersion

object URLProviderUtils {

    val mVareaUrl: String
        get() = ("http://mapi.yinyuetai.com/video/get_mv_areas.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}")

    /**
     * 获取V榜地址
     *
     * @return
     */
    val vChartAreasUrl: String
        get() = ("http://mapi.yinyuetai.com/vchart/get_vchart_areas.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}")

    private val systemVersion: String
        get() = android.os.Build.VERSION.RELEASE

    private val phoneModel: String
        get() = android.os.Build.MODEL

    /**
     * 获取首页的url
     *
     * @param offset 数据偏移量
     * @param size   返回数据的条目个数
     * @return url
     */
    fun getHomeUrl(offset: Int, size: Int): String {
        val url = ("http://mapi.yinyuetai.com/suggestions/front_page.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}"
                + "&offset=" + offset
                + "&size=" + size
                + "&v=4&rn=640*540")
        Log.i("Main_url", url)
        return url
    }

    fun getMVListUrl(area: String, offset: Int, size: Int): String {
        return ("http://mapi.yinyuetai.com/video/list.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}"
                + "&area=" + area
                + "&offset=" + offset
                + "&size=" + size)
    }

    fun getYueDanUrl(offset: Int, size: Int): String {
        val url = ("http://mapi.yinyuetai.com/playlist/list.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}"
                + "&offset=" + offset
                + "&size=" + size)
        Log.i("YueDan_url", url)
        return url
    }

    /**
     *
     * 获取音乐节目列表
     *
     * @param artistIds
     * @param offset
     * @param size
     * @return
     */
    fun getYinYueProgramList(artistIds: String, offset: Int, size: Int): String {
        return ("http://mapi.yinyuetai.com/playlist/show.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}"
                + "&offset=" + offset
                + "&size=" + size
                + "&artistIds=" + artistIds)
    }

    /**
     * 获取V榜的周期
     *
     * @return
     */
    fun getVChartPeriodUrl(area: String): String {
        return ("http://mapi.yinyuetai.com/vchart/period.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}"
                + "&area=" + area)
    }

    /**
     * 获取V榜列表
     *
     * @param area
     * @param dateCode
     * @return
     */
    fun getVChartListUrl(area: String, dateCode: Int): String {
        return ("http://mapi.yinyuetai.com/vchart/show.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}"
                + "&area=" + area
                + "&datecode=" + dateCode)
    }

    /**
     * 获取相关MV
     *
     * @param id
     * @return
     */
    fun getRelativeVideoListUrl(id: Int): String {
        return ("http://mapi.yinyuetai.com/video/show.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}"
                + "&relatedVideos=true"
                + "&id=" + id)
    }

    /**
     * 通过id 获取某人的悦单
     *
     * @param id
     * @return
     */
    fun getPeopleYueDanList(id: Int): String {
        return ("http://mapi.yinyuetai.com/playlist/show.json?deviceinfo="
                + "{\"aid\":\"10201036\",\"os\":\"Android\","
                + "\"ov\":" + "\"" + systemVersion + "\"" + ","
                + "\"rn\":\"480*800\","
                + "\"dn\":" + "\"" + phoneModel + "\"" + ","
                + "\"cr\":\"46000\","
                + "\"as\":"
                + "\"WIFI\","
                + "\"uid\":"
                + "\"dbcaa6c4482bc05ecb0bf39dabf207d2\","
                + "\"clid\":110025000}"
                + "&id=" + id)
    }
}

