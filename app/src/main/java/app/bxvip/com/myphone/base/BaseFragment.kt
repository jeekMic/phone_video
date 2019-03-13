package app.bxvip.com.hbyinyin.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

abstract  class BaseFragment :Fragment(),AnkoLogger{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * 显示布局
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return initView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initListener()
        initData()
    }
    open protected fun init(){

    }

    /**
     * daapter listener
     */
    open protected fun initListener(){

    }

    /**
     * 数据的初始化
     */
    open protected fun initData(){

    }

    /**
     * 获取布局的ID
     */
    abstract fun initView():View

    fun myToast(msg:String){
        context!!.runOnUiThread { toast(msg) }
    }
}