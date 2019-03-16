package app.bxvip.com.myphone.net

interface ResponseHandler<RESPONSE> {
    fun onError(type:Int,msg: String?)
    fun onSuccess(type:Int,result: RESPONSE?)
}