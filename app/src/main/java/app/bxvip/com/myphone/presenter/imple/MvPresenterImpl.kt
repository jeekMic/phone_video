package app.bxvip.com.myphone.presenter.imple

import app.bxvip.com.myphone.net.MvAreaRequest
import app.bxvip.com.myphone.net.ResponseHandler
import app.bxvip.com.myphone.presenter.interf.MvPresenter
import app.bxvip.com.myphone.view.MvView
import com.itheima.player.model.bean.MvAreaBean

class MvPresenterImpl(var mvView: MvView) :MvPresenter, ResponseHandler<List<MvAreaBean>> {
    override fun onError(type: Int, msg: String?) {
        mvView.onError(msg)
    }

    override fun onSuccess(type: Int, result: List<MvAreaBean>?) {
        mvView.loadSuccess(result)
    }

    override fun destoryView() {

    }

    override fun loadDatas() {
        MvAreaRequest(this).execute()
    }

    override fun loadMore(i: Int) {

    }


}
