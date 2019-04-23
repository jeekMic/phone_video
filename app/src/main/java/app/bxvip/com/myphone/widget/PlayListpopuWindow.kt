package app.bxvip.com.myphone.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.PopupWindow
import app.bxvip.com.myphone.R

class PlayListpopuWindow(context: Context,adapter: BaseAdapter,listener: AdapterView.OnItemClickListener, var window: Window):PopupWindow() {
    var alpha:Float = 0f
    init {
        //记录当前的窗体的透明度
        alpha = window.attributes.alpha
        //设置布局
        val view = LayoutInflater.from(context).inflate(R.layout.pop_playlist,null, false)
        val listview = view.findViewById<ListView>(R.id.listView)
        listview.adapter = adapter
        //设置条目点击事件
        listview.setOnItemClickListener(listener)
        contentView = view
        //设置宽度和高度
        width = ViewGroup.LayoutParams.MATCH_PARENT
        val manager =  context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val position = Point()
        manager.defaultDisplay.getSize(position)
        val windowH = position.y
        height = windowH*3/5
        isFocusable= true
        //设置外部点击
        isOutsideTouchable = true
        //设置能响应返回按钮,低版本不设置这个会报错
        setBackgroundDrawable(ColorDrawable())
        /**
         * 处理动画
         */
        animationStyle = R.style.pop
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int) {
        super.showAsDropDown(anchor, xoff, yoff)
        //已经显示
        val attributes = window.attributes
        attributes.alpha = 0.8f
        //s设置到应用程序
        window.attributes = attributes
    }

    override fun dismiss() {
        super.dismiss()
        //隐藏
        //已经显示
        val attributes = window.attributes
        attributes.alpha = 1.0f
        window.attributes = attributes
    }
}