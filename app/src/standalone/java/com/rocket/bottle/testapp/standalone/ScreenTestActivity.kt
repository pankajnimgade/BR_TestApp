package com.rocket.bottle.testapp.standalone

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.rocket.bottle.testapp.R
import kotlinx.android.synthetic.standalone.activity_screen_test.*

class ScreenTestActivity : AppCompatActivity(), ScreenClickListener {

    companion object {

        private val TAG = ScreenTestActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_test)

        initializeUi()
    }

    private fun initializeUi() {

        val adapter = ScreenAdapter(ScreenUtils.getScreenList(), this)
        recycler_view.adapter = adapter
    }

    override fun onScreenClick(screen: Screen) {
        Log.d(TAG, ": ${screen.name}")
        startActivity(Intent(baseContext, screen.activityName))
    }

}
