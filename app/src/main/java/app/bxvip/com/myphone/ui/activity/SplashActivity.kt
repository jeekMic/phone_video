package app.bxvip.com.hbyinyin.ui.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPropertyAnimatorListener
import android.view.View
import app.bxvip.com.hbyinyin.base.BaseActivity
import app.bxvip.com.myphone.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity(), ViewPropertyAnimatorListener {

    override fun getlayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun initData() {
        ViewCompat.animate(imageView).scaleX(1.0f).scaleY(1.0f).setListener(this).setDuration(2000)
    }


    override fun onAnimationEnd(p0: View?) {
        startActivityAndFinish<MainActivity>()
    }

    override fun onAnimationCancel(p0: View?) {
    }

    override fun onAnimationStart(p0: View?) {
    }

}