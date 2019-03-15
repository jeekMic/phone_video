package app.bxvip.com.myphone.net

interface ResponseHandler<RESPONSE> {
    fun onError(msg: String?)
    fun onSuccess(result: RESPONSE?)
}