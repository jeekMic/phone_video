package app.bxvip.com.myphone.util

import android.os.Environment
import java.io.File

object LyricLoader {
    val dir = File(Environment.getExternalStorageDirectory(),"Download/lrc")
    fun loadLyricFile(displayerName:String): File {
        return File(dir,displayerName+".lrc")
    }
}