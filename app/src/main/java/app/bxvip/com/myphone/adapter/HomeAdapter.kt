package app.bxvip.com.myphone.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import app.bxvip.com.myphone.widget.HomeItemView
import app.bxvip.com.myphone.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    private var list = ArrayList<HomeItemBean>()
    fun updateList(list: List<HomeItemBean>?){
        list?.let{
            this.list.clear()
            this.list.addAll(list)
            this.notifyDataSetChanged()
        }
    }

    //在当前的基础上再来加载数据
    fun loadMoreData(list: List<HomeItemBean>?){
            list?.let {
                this.list.addAll(list)
                notifyDataSetChanged()
            }
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HomeHolder {
        if (p1==1){
            //最后一条
            return HomeHolder(LoadMoreView(p0.context))
        }else{
            //普通条目
            return HomeHolder(HomeItemView(p0.context))
        }
    }

    override fun getItemCount(): Int {
        return list.size+1
    }

    override fun getItemViewType(position: Int): Int {
        if (position==list.size){
            return 1
        }else{
            return 0
        }
    }
    override fun onBindViewHolder(p0: HomeHolder, p1: Int) {
        //如果是最后一条， 就不用刷新数据
        if (p1==list.size){
            return
        }
        val data = list.get(p1)
        val itemView = p0.itemView as HomeItemView
        //条目刷新
        itemView.setData(data)


    }


    class HomeHolder(itemview: View): RecyclerView.ViewHolder(itemview) {

    }
}