package app.bxvip.com.myphone.ui.fragment

import android.Manifest
import android.content.AsyncQueryHandler
import android.content.pm.PackageManager
import android.database.Cursor
import android.provider.MediaStore
import android.view.View
import app.bxvip.com.hbyinyin.base.BaseFragment
import app.bxvip.com.myphone.adapter.VbangAdapter
import android.support.v4.app.ActivityCompat
import app.bxvip.com.myphone.ui.activity.AudioPlayerActivity
import com.itheima.player.model.AudioBean
import kotlinx.android.synthetic.main.fragment_vbang.*
import org.jetbrains.anko.noButton
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.yesButton



class VBangFragment : BaseFragment() {
//    var handler =object:Handler(){
//        override fun handleMessage(msg: Message?) {
//            msg?.let {
//                val cursor = msg.obj as Cursor
//            }
//        }
//    }
    override fun initView(): View {
       return View.inflate(context, app.bxvip.com.myphone.R.layout.fragment_vbang, null)
    }

    override fun initData() {
        handlePermission()
    }

    //动态申请权限
    private fun handlePermission(){
        val permission = Manifest.permission.READ_EXTERNAL_STORAGE
        //查看是否有权限
        val checkPermissopn = context?.let { ActivityCompat.checkSelfPermission(it, permission) }
        if (checkPermissopn == PackageManager.PERMISSION_GRANTED){
            //已经获取权限
            loadData()
        }else{
            //没有获取权限
            if (activity?.let { ActivityCompat.shouldShowRequestPermissionRationale(it,permission) }!!){
                //需要弹出对话框
                alert( "我们只有访问一弄音乐文件，不会访问隐私照片"){
                   yesButton {
                       myRequerstPermission()
                       noButton {  }
                   }
                }.show()
            }else{
                //不需要弹出
                myRequerstPermission()
            }
        }

    }

    /**
     * 接收权限
     * requestCode请求码
     * permission 权限申请数组
     * grantResult 申请之后的结果
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            loadData()
        }
    }


    private fun myRequerstPermission() {
        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        requestPermissions(permissions,1)
    }

    fun loadData() {
        //加载音乐列表数据
        val resolver = context?.contentResolver
        //        Thread(object :Runnable{
        //            override fun run() {
        //                val  cursor = resolver?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
        //                        arrayOf(MediaStore.Audio.Media.DATA,
        //                                MediaStore.Audio.Media.SIZE,
        //                                MediaStore.Audio.Media.DISPLAY_NAME,
        //                                MediaStore.Audio.Media.ARTIST),null,null,null
        //                )
        //                val msg = Message.obtain()
        //                msg.obj = cursor
        //                handler.sendMessage(msg)
        //                //cursor?.let { CursorUtil.logCursor(it) }
        //            }
        //
        //        }).start()
        //        AudioTask().execute(resolver)
        val handler = object : AsyncQueryHandler(resolver) {
            //查询完成的回调
            override fun onQueryComplete(token: Int, cookie: Any?, cursor: Cursor?) {
                //回调后是在主线程中
                print("\n搜索成功了\n")
                //刷新列表
                // (cookie as VbangAdapter).notifyDataSetChanged()
    //                CursorUtil.logCursor(cursor!!)
                (cookie as VbangAdapter).swapCursor(cursor)
            }

        }
        handler.startQuery(0, adapter, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, arrayOf(
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.SIZE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST), null, null, null)
    }


    //    class AudioTask:AsyncTask<ContentResolver, Void, Cursor>(){
//        override fun doInBackground(vararg params: ContentResolver?): Cursor {
//            //在后台线程中执行
//                            val  cursor = params[0]?.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
//                        arrayOf(MediaStore.Audio.Media.DATA,
//                                MediaStore.Audio.Media.SIZE,
//                                MediaStore.Audio.Media.DISPLAY_NAME,
//                                MediaStore.Audio.Media.ARTIST),null,null,null )
//
//
//            return cursor!!
//        }
//
//        override fun onPostExecute(result: Cursor?) {
//            super.onPostExecute(result)
//            //打印
//            CursorUtil.logCursor(result!!)
//        }
//
//
//    }
    var adapter:VbangAdapter? = null
    override fun initListener() {
        adapter = VbangAdapter(context,null)
        listView.adapter = adapter
        listView.setOnItemClickListener{
            adapterView, view, i,l->
            //获取数据集合
            val cursor = adapter?.getItem(i)
            //位置position 通过当前cursor位获取整个播放列表
            val list:ArrayList<AudioBean> = AudioBean.getAudioBeans(cursor as Cursor?)
            //跳转到音乐播放器的界面
            startActivity<AudioPlayerActivity>("list" to list, "position" to i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        //关闭cursor
        //设置为null
        adapter?.changeCursor(null)
    }

}