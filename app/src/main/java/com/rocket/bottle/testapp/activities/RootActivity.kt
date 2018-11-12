package com.rocket.bottle.testapp.activities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup

abstract class RootActivity : AppCompatActivity(), ActivityListener {

    protected lateinit var rootView: ViewGroup

    override fun onNoNetwork() {
        Snackbar.make(rootView, "No network connection :(", Snackbar.LENGTH_LONG).show()
    }

    override fun onApiCallError() {
        Snackbar.make(rootView, "Something went wrong :(", Snackbar.LENGTH_LONG).show()
    }

    fun checkNetworkConnection(): Boolean {
        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

}

interface ActivityListener {

    fun onNoNetwork()

    fun onApiCallError()
}