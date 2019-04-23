package app.bxvip.com.myphone.util

object  StringUtil {
    val HOUR = 60*60*1000
    val MIN = 60*1000
    val SEC = 1000
    fun parseDuration(progress:Int): String {
        val hour = progress/ HOUR
        val min = progress%HOUR /MIN
        val sec = progress%HOUR%MIN /SEC
        var result:String = ""
        //不足一小时 不显示小时
        if (hour == 0){
            //不足一小时
            result = String.format("%02d:%02d",min, sec)
            println("--$result---$min---$sec")
        }else{
            result = String.format("%02d:%02d:%02d",hour,min, sec)
        }
        return result
    }
}
