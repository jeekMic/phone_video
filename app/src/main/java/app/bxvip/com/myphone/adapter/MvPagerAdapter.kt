package app.bxvip.com.myphone.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import app.bxvip.com.myphone.ui.fragment.MvPagerFragment
import com.itheima.player.model.bean.MvAreaBean

class MvPagerAdapter(val context:Context,val list:List<MvAreaBean>?,fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    override fun getItem(p0: Int): Fragment {
//        val fragment = MvPagerFragment()
        val bundle = Bundle()
        println("传入数据")
        bundle.putString("code", list?.get(p0)?.code)
//        fragment.arguments = bundle
        val fragment = Fragment.instantiate(context,MvPagerFragment::class.java.name, bundle)
        return fragment
    }

    override fun getCount(): Int {
        //如果不为空则返回 list.size 为空返回0
        return list?.size?:0
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return list?.get(position)?.name
    }
}