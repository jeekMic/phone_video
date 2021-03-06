package app.bxvip.com.hbyinyin.base

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle

import android.support.v7.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions

import org.jetbrains.anko.*
import android.Manifest.permission
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.support.v4.app.FragmentActivity
import io.reactivex.functions.Consumer


/**
 * 所有activity的基类
 */
abstract class BaseActivity: AppCompatActivity() ,AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getlayoutId())
        initView()
        initListener()
        initData()

    }

    /**
     * 初始化数据
     */
    open protected fun initData(){

    }
    /**
     * 初始化数据
     */
    open protected fun initView(){

    }

    /**
     * listener adapter
     */
    open protected fun initListener(){

    }

    /**
     * 获取布局ID
     */
    abstract fun  getlayoutId():Int

    protected  fun myToast(msg:String){

        runOnUiThread {
            toast(msg)
        }
    }
    //开启一个activity并且销毁当前的activity
    inline  fun <reified T:BaseActivity> startActivityAndFinish(){
        startActivity<T>()
        finish()
    }

}