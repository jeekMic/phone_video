package app.bxvip.com.myphone.widget


import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import app.bxvip.com.myphone.R
import com.itheima.player.model.bean.VideosBean
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mv.view.*

/**
 * Mv界面每一个条目的view
 */
class MvItemView:RelativeLayout {


    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init {
        //创建这个布局并立即添加到当前的相对布局当中去
        View.inflate(context, R.layout.item_mv, this)
    }

    /**
     * 设配每一个条目，这里也就是给itemview设置数据
     */
    fun setData(data: VideosBean) {
        artist.text = data.artistName
        title.text = data.title
        Picasso.get().load(data.playListPic).into(bg)
    }
}