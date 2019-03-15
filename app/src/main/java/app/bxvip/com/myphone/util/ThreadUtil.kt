package app.bxvip.com.myphone.util

import android.os.Handler
import android.os.Looper


object ThreadUtil {
    val handler = Handler(Looper.getMainLooper())

    fun runOnMainThread(runable:Runnable){
        handler.post(runable)
    }


}