package app.bxvip.com.myphone.adapter

import android.content.Context
import app.bxvip.com.myphone.base.BaseListAdapter
import app.bxvip.com.myphone.widget.MvItemView
import com.itheima.player.model.bean.VideosBean

class MvListAdapter: BaseListAdapter<VideosBean, MvItemView>() {
    override fun refreshItemView(itemView: MvItemView, data: VideosBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): MvItemView {
        return MvItemView(context)
    }
}