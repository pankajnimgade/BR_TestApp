package com.rocket.bottle.testapp.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.rocket.bottle.testapp.R

/**
 * This is an entry to the Application, It shows where I want to work :)
 * */
class SplashActivity : RootActivity() {

    private val mHandler = Handler()
    private val mSplashRunnable = Runnable {
        startActivity(Intent(baseContext, StoreListActivity::class.java))
        finish()
    }

    companion object {
        private const val SPLASH_DELAY = 1500L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onStart() {
        super.onStart()
        mHandler.postDelayed(mSplashRunnable, SPLASH_DELAY)
    }

    override fun onStop() {
        super.onStop()
        mHandler.removeCallbacks(mSplashRunnable)
    }
}
