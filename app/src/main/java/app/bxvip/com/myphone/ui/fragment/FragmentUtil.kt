package app.bxvip.com.myphone.ui.fragment

import android.support.v4.app.Fragment
import app.bxvip.com.myphone.R


class FragmentUtil private constructor(){ //这样写表示私有化构造方法\
    val homeFragment by lazy { HomeFragment() }
    val mvFragment by lazy { MvFragment() }
    val vbangFragment by lazy { VBangFragment() }
    val yuedanFragment by lazy { YueDanFragment() }
    /**
     * companion object 修饰为伴生对象,伴生对象在类中只能存在一个，类似于java中的静态方法 Java 中使用类访问静态成员，静态方法。
     * 用object修饰的kotlin类表示的是这个类就是一个单例
     */
    companion object {
        //by lazy 是线程安全的操作
        val fragmentUtil by lazy{ FragmentUtil() }
    }

    fun getFragment(tabId:Int): Fragment? {
        when(tabId){
            R.id.tab_home -> return homeFragment
            R.id.tab_mv -> return mvFragment
            R.id.tab_vbang -> return vbangFragment
            R.id.tab_yuedan -> return yuedanFragment
        }
        return null

    }

}