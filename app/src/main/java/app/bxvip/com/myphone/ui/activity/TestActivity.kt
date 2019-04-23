package app.bxvip.com.myphone.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import io.vov.vitamio.utils.Log
import java.io.IOException
import java.util.concurrent.TimeUnit
import android.os.AsyncTask.execute
import kotlinx.android.synthetic.main.activity_test.*
import okhttp3.*
import android.R.string
import android.os.AsyncTask.execute
import okhttp3.OkHttpClient
import android.R
import android.support.annotation.Nullable
import okio.BufferedSink
import okhttp3.RequestBody
import okhttp3.MultipartBody

class TestActivity : AppCompatActivity() {
    //    var client:OkHttpClient? = null
//    var response:Response? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(app.bxvip.com.myphone.R.layout.activity_test)
//        Thread {
//            val client = OkHttpClient()
////            val mediaTyp:MediaType = MediaType.parse("text/x-markdown; charset=utf-8")!!
////            var requestBody:String = "hello"
////            val body = RequestBody.create(mediaTyp, requestBody)
//            val requestBody = object : RequestBody() {
//                @Nullable
//                override fun contentType(): MediaType? {
//                    return MediaType.parse("text/x-markdown; charset=utf-8")
//                }
//
//                @Throws(IOException::class)
//                override fun writeTo(sink: BufferedSink) {
//                    sink.writeUtf8("I am Jdqm.")
//                }
//            }
//
//
//            val body = MultipartBody.Builder("AaB03x")
//                    .setType(MultipartBody.FORM)
//                    .addPart(
//                            Headers.of("Content-Disposition", "form-data; name=\"title\""),
//                            RequestBody.create(null, "Square Logo"))
//                    .addPart(
//                            Headers.of("Content-Disposition", "form-data; name=\"image\""),
//                            RequestBody.create("", File("website/static/logo-square.png")))
//                    .build()
//
//
//            val request = Request.Builder()
//                    .url("http://www.baidu.com")
//                    .post(requestBody)
//                    .build()
//            val call = client.newCall(request)
//            call.enqueue(object : Callback {
//                override fun onFailure(call: Call, e: IOException) {
//                    android.util.Log.i("hello----", "failed")
//                }
//
//                @Throws(IOException::class)
//                override fun onResponse(call: Call, response: Response) {
//                    android.util.Log.i("hello----", response.body()!!.string())
//                }
//            })
//        }.start()
    }


}
