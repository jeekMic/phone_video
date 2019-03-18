package app.bxvip.com.myphone.presenter.imple

import app.bxvip.com.myphone.base.BaseListPresenter
import app.bxvip.com.myphone.base.BaseView
import app.bxvip.com.myphone.net.ResponseHandler
import app.bxvip.com.myphone.net.YueDanRequest
import app.bxvip.com.myphone.presenter.interf.YueDanpresenter
import app.bxvip.com.myphone.view.YueDanView
import com.itheima.player.model.bean.YueDanBean

class YueDanpresenterImpl(var yueDanView: BaseView<YueDanBean>?) : YueDanpresenter, ResponseHandler<YueDanBean> {

    /**
     * 解绑
     */
    override fun destoryView(){
        if (yueDanView!=null){
            yueDanView = null
        }
    }
    override fun onError(type: Int, msg: String?) {
        yueDanView?.onError(msg)
    }

    override fun onSuccess(type: Int, result: YueDanBean?) {
        if (type==BaseListPresenter.TYPE_INIT_OR_REFRESH){
            yueDanView?.loadSuccess(result)
        }else if (type==BaseListPresenter.TYPE_LOAD_MORE){
            yueDanView?.loadMore(result)
        }
    }

    override fun loadDatas() {
        YueDanRequest(BaseListPresenter.TYPE_INIT_OR_REFRESH,0,this).execute()
    }

    override fun loadMore(i: Int) {
        YueDanRequest(BaseListPresenter.TYPE_LOAD_MORE,i,this).execute()
    }
}