package app.bxvip.com.hbyinyin.base

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.toast
import org.jetbrains.anko.verbose

/**
 * 所有activity的基类
 */
abstract class BaseActivity: AppCompatActivity() ,AnkoLogger{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getlayoutId())

        initListener()
        initData()

    }

    /**
     * 初始化数据
     */
    open protected fun initData(){

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
}