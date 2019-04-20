package app.bxvip.com.myphone.util

import android.database.Cursor
import java.util.*
//使用object类声明表示的这个类是一个静态的类，类里面 的方法都是静态的方法
object CursorUtil{
    fun logCursor(cursor: Cursor){
        cursor.let {
            //将游标复位
            it.moveToPosition(-1)
            while (it.moveToNext()){
                //it.columnCount 表示当前游标内有多少数据
                // it.count 表示的是游标可以向下跳转多少次
                for (index in 0 until  it.columnCount){
                    println("key=======${it.getColumnName(index)}---value====${it.getString(index)}")
                }
                println("=========================下一条数据")

            }
        }
    }
}