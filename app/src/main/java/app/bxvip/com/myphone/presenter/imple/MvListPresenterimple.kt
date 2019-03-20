package app.bxvip.com.myphone.presenter.imple

import app.bxvip.com.myphone.base.BaseListPresenter
import app.bxvip.com.myphone.net.MvListRequest
import app.bxvip.com.myphone.net.ResponseHandler
import app.bxvip.com.myphone.presenter.interf.MvListPresenter
import app.bxvip.com.myphone.view.MvListView
import com.itheima.player.model.bean.MvPagerBean

class MvListPresenterimple(var code:String, var mvListView:MvListView?):MvListPresenter,ResponseHandler<MvPagerBean> {
    override fun onError(type: Int, msg: String?) {
        mvListView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: MvPagerBean?) {
        if (type==BaseListPresenter.TYPE_INIT_OR_REFRESH){
            mvListView?.loadSuccess(result)
        }else if(type ==BaseListPresenter.TYPE_LOAD_MORE){
            mvListView?.loadMore(result)
        }
    }

    override fun destoryView() {
        if (mvListView!=null){
            mvListView =null
        }
    }

    override fun loadDatas() {
        MvListRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH,code,0, this).execute()
    }

    override fun loadMore(i: Int) {
        MvListRequest(BaseListPresenter.TYPE_LOAD_MORE,code,0, this).execute()
    }
}