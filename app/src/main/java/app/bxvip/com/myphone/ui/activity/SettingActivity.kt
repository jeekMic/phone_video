package app.bxvip.com.myphone.ui.activity

import android.preference.PreferenceManager
import android.support.v7.widget.Toolbar
import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.util.ToolBarManager
import org.jetbrains.anko.find

class SettingActivity :BaseActivity(), ToolBarManager{
    //惰性加载
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar)}
    override fun getlayoutId(): Int {
        return R.layout.activity_setting
    }

    override fun initData() {
        initSettingToolBar()
       var sp =  PreferenceManager.getDefaultSharedPreferences(this)
        var push = sp.getBoolean("push",false)
        println(push)
    }
}