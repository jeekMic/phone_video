package app.bxvip.com.myphone.adapter

import android.content.Context
import app.bxvip.com.myphone.base.BaseListAdapter
import app.bxvip.com.myphone.widget.HomeItemView
import com.itheima.player.model.bean.HomeItemBean


class HomeAdapter: BaseListAdapter<HomeItemBean, HomeItemView>() {
    override fun refreshItemView(itemView: HomeItemView, data: HomeItemBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): HomeItemView {
        return HomeItemView(context)
    }

}