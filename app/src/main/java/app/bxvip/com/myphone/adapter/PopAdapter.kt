package app.bxvip.com.myphone.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import app.bxvip.com.myphone.widget.PopuListItemView
import com.itheima.player.model.AudioBean

class PopAdapter(var list:List<AudioBean>):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView:View? = null
        if (convertView==null){
            itemView = PopuListItemView(parent?.context)
        }else{
            itemView = convertView as PopuListItemView
        }
        itemView.setData(list.get(position))
        return itemView
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}