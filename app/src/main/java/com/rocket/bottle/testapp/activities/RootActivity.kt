package com.rocket.bottle.testapp.activities

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup

/**
 * This should be extended by Activities which need to show following SnackBars
 * */
abstract class RootActivity : AppCompatActivity(), ActivityListener {

    protected lateinit var rootView: ViewGroup

    override fun onNoNetwork() {
        Snackbar.make(rootView, "No network connection :(", Snackbar.LENGTH_LONG).show()
    }

    override fun onApiCallError() {
        Snackbar.make(rootView, "Something went wrong :(", Snackbar.LENGTH_LONG).show()
    }

}

interface ActivityListener {

    fun onNoNetwork()

    fun onApiCallError()
}