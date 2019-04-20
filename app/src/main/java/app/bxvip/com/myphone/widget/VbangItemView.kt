package app.bxvip.com.myphone.widget

import android.content.Context
import android.text.format.Formatter
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import app.bxvip.com.myphone.R
import com.itheima.player.model.AudioBean
import kotlinx.android.synthetic.main.item_vbang.*
import kotlinx.android.synthetic.main.item_vbang.view.*

class VbangItemView: RelativeLayout{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
   init {
       View.inflate(context, R.layout.item_vbang,this)
   }
    fun setData(iteamBean: AudioBean){
        title.text = iteamBean.display_name
        artist.text = iteamBean.artist
        size.text = Formatter.formatFileSize(context, iteamBean.size)
    }
}