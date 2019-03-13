package app.bxvip.com.hbyinyin.ui.activity


import android.os.Bundle
import android.support.v7.widget.Toolbar

import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import app.bxvip.com.myphone.ui.fragment.FragmentUtil
import app.bxvip.com.myphone.util.ToolBarManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find

class MainActivity : BaseActivity() , ToolBarManager {
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar)}

    override fun getlayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {

    }

    override fun initData() {
        iniMainToolBar()
    }

    override fun initListener() {
        //设置tab切换的监听
        bottomBar.setOnTabSelectListener {
            //it 代表 tabId
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, FragmentUtil.fragmentUtil.getFragment(it)!!,it.toString())
            transaction.commit()
        }
    }
}
