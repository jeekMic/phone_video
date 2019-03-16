package app.bxvip.com.myphone.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import app.bxvip.com.myphone.widget.LoadMoreView
import app.bxvip.com.myphone.widget.YueDanItemView
import com.itheima.player.model.bean.YueDanBean

class YueDanAdapter : RecyclerView.Adapter<YueDanAdapter.YueDanHolder>() {
    private var list = ArrayList<YueDanBean.PlayListsBean>()
    fun updateList(list: List<YueDanBean.PlayListsBean>?){
        list?.let{
            this.list.clear()
            this.list.addAll(list)
            this.notifyDataSetChanged()
        }
    }

    //在当前的基础上再来加载数据
    fun loadMoreData(list: List<YueDanBean.PlayListsBean>?){
            list?.let {
                this.list.addAll(list)
                notifyDataSetChanged()
            }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): YueDanHolder {
        if (p1==1){
            //最后一条
            return YueDanHolder(LoadMoreView(p0.context))
        }else{
            //普通条目
            return YueDanHolder(YueDanItemView(p0.context))
        }

    }

    override fun getItemCount(): Int {
        return list.size+1
    }

    //获取不用条目的view样式
    override fun getItemViewType(position: Int): Int {
        if (position==list.size){
            //最后一条
            return 1
        }else{
            //普通的一条数据
            return 0
        }
    }
    override fun onBindViewHolder(p0: YueDanHolder, p1: Int) {
        //如果是最后一条， 就不用刷新数据
        if (p1==list.size){
            return
        }
        val data = list.get(p1)
        val itemView = p0.itemView as YueDanItemView
        //条目刷新
        itemView.setData(data)


    }


    class YueDanHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

    }
}