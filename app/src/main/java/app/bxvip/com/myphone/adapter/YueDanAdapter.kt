package app.bxvip.com.myphone.adapter

import android.content.Context
import app.bxvip.com.myphone.base.BaseListAdapter
import app.bxvip.com.myphone.widget.YueDanItemView
import com.itheima.player.model.bean.YueDanBean


class YueDanAdapter : BaseListAdapter<YueDanBean.PlayListsBean, YueDanItemView>(){
    override fun refreshItemView(itemView: YueDanItemView, data: YueDanBean.PlayListsBean) {
        itemView.setData(data)
    }

    override fun getItemView(context: Context?): YueDanItemView {
        return  YueDanItemView(context)
    }

}