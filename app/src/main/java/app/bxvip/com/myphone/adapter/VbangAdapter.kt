package app.bxvip.com.myphone.adapter

import android.content.Context
import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import app.bxvip.com.myphone.widget.VbangItemView
import com.itheima.player.model.AudioBean

class VbangAdapter(context:Context?, c: Cursor?): CursorAdapter(context, c){
    override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
            return VbangItemView(context)
    }

    override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
        val itemView = view as VbangItemView
        //data
        val itemBean = AudioBean.getAudioBean(cursor)
        //view+data
        itemView.setData(itemBean)
    }

}