package app.bxvip.com.myphone.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import app.bxvip.com.myphone.widget.HomeItemView
import app.bxvip.com.myphone.widget.LoadMoreView
import com.itheima.player.model.bean.HomeItemBean
import java.security.AccessControlContext

abstract class BaseListAdapter<ITEMBEAN, ITEMVIEW:View>  : RecyclerView.Adapter<BaseListAdapter.BaseListHolder>() {
    private var list = ArrayList<ITEMBEAN>()
    fun updateList(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.clear()
            this.list.addAll(list)
            this.notifyDataSetChanged()
        }
    }

    //在当前的基础上再来加载数据
    fun loadMoreData(list: List<ITEMBEAN>?) {
        list?.let {
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): BaseListHolder {
        if (p1 == 1) {
            //最后一条
            return BaseListHolder(LoadMoreView(p0.context))
        } else {
            //普通条目
            return BaseListHolder(getItemView(p0.context))
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        if (position == list.size) {
            return 1
        } else {
            return 0
        }
    }

    override fun onBindViewHolder(p0: BaseListAdapter.BaseListHolder, p1: Int) {
        //如果是最后一条， 就不用刷新数据
        if (p1 == list.size) {
            return
        }
        val data = list.get(p1)
        val itemView = p0.itemView as ITEMVIEW
        //条目刷新
//        itemView.setData(data)
        refreshItemView(itemView, data)
        print("点击事件")
        itemView.setOnClickListener {
            //RecycleView条目的点击事件
            listener?.let {
                it(data)
                print("set innvoke")
            }
//            listener?.invoke(data)
        }
    }


    var listener: ((itemBiean:ITEMBEAN) -> Unit?)? = null
    fun setMyListener(listener:(itemBiean:ITEMBEAN)-> Unit){
        this.listener = listener
    }


    abstract fun refreshItemView(itemView: ITEMVIEW,data:ITEMBEAN)
    //获取条目的view
    abstract fun getItemView(context: Context?):ITEMVIEW

    class BaseListHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
    }
}