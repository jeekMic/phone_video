package app.bxvip.com.myphone.ui.fragment

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.TextView
import app.bxvip.com.hbyinyin.base.BaseFragment


class DefaultFragment : BaseFragment() {
    override fun initView(): View {
        val textView = TextView(context)
        textView.gravity = Gravity.CENTER
        textView.setTextColor(Color.RED)
        textView.setText("hello")
        return textView
    }
}