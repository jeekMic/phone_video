package app.bxvip.com.myphone.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import app.bxvip.com.myphone.R
import com.itheima.player.model.bean.YueDanBean
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.item_yuedan.view.*

/**
 * 悦单条目的自定义view
 */
class YueDanItemView : RelativeLayout{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    init{
        View.inflate(context, R.layout.item_yuedan,this)
    }

    /**
     * view的条目初始化
     */
    fun setData(list: YueDanBean.PlayListsBean){
        //歌单名称
        title.text = list.title
        author_name.text = list.creator?.nickName
        count.text = list.videoCount.toString()
        //背景
        Picasso.get().load(list.playListBigPic).into(bg)
        println(list.creator?.largeAvatar)
        println("---------------------------------")
        Picasso.get().load(list.creator?.largeAvatar).transform(CropCircleTransformation()).into(author_image)
    }

}