package app.bxvip.com.myphone.base

interface BaseListPresenter {
    /**
     * 解绑
     */
    fun destoryView()
    companion object {
        val TYPE_INIT_OR_REFRESH = 1
        val TYPE_LOAD_MORE= 2
    }
    fun loadDatas()
    fun loadMore(i: Int)
}