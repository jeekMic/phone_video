package app.bxvip.com.myphone.util

import android.content.Intent
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.Toast
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.ui.activity.SettingActivity
import org.jetbrains.anko.startActivity


interface ToolBarManager {
    val toolbar: Toolbar
    /**
     * 初始主界面的中的ToolBar
     */
    fun iniMainToolBar(){
        toolbar.setTitle("标哥影音")
        toolbar.inflateMenu(R.menu.main)
        toolbar.setOnMenuItemClickListener(object: Toolbar.OnMenuItemClickListener{
            override fun onMenuItemClick(p0: MenuItem?): Boolean {
                when(p0?.itemId){
                    R.id.setting ->{
                        toolbar.context.startActivity(Intent(toolbar.context,SettingActivity::class.java))
                    }
                }
                return true
            }

        })
    }

    fun initSettingToolBar(){
        toolbar.setTitle("设置界面")
    }
}