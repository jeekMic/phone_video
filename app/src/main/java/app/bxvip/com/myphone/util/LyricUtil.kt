package app.bxvip.com.myphone.util

import com.itheima.player.model.LyriBean
import java.io.*
import java.nio.charset.Charset

object LyricUtil {
    fun parseLyric(file: File):List<LyriBean>{
        //创建集合
        val list = ArrayList<LyriBean>()
        //判断集合是否为空
        if(!file.exists()){
            list.add(LyriBean(0,"添加歌词出错"))
            return list
        }
        //解析歌词文件
//        val bfr = BufferedReader(InputStreamReader(FileInputStream(file), "gbk"))
//        var line = bfr.readLine()
//        while (line!=null){
//            //解析
//            line = bfr.readLine()
//        }
        //返回的是每一行的集合
        val linesList = file.readLines(Charset.forName("utf-8"))
        for (line in linesList){
            //解析一行
            val lineList:List<LyriBean> = ParseLine(line)
            //添加到集合当中去
            list.addAll(lineList)
        }
        //集合排序
        list.sortBy { it.startTime }
        //返回集合
        return list
    }

    /**
     * 解析一行歌词
     */
    private fun ParseLine(line: String): List<LyriBean> {
        //创建一个空的集合
        val list = ArrayList<LyriBean>()
        //解析当前行
        val arr = line.split("]")
        //获取歌词的内容
        val content = arr.get(arr.size-1)
        println("hb===${content}")
        for (index in 0 until arr.size-1){
            val startTime:Int = parseTime(arr.get(index))
            list.add(LyriBean(startTime,content))
        }
        //返回集合
        return list
    }

    private fun parseTime(get: String): Int {
        //先去掉前面的括号
        val timeString = get.substring(1)
        val list = timeString.split(":")
        var hour = 0
        var min = 0
        var sec = 0f
        if(list.size==3){
            //是有小时的
            hour = list[0].toInt()*60*60*1000
            min = list[1].toInt()*60*1000
            sec = list[2].toFloat()*1000
        }else{
            min = list[0].toInt()*60*1000
            sec = list[1].toFloat()*1000
            //是没有小时的
        }
        return (hour+min+sec).toInt()
    }
}