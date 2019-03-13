package app.bxvip.com.myphone.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import app.bxvip.com.hbyinyin.base.BaseFragment

class VBangFragment : BaseFragment() {
    override fun initView(): View {
       val tv = TextView(context)
        tv.gravity = Gravity.CENTER
        tv.setTextColor(Color.RED)
        tv.setText(javaClass.simpleName)
        return  tv
    }
}