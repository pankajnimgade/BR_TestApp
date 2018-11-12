package com.rocket.bottle.testapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.squareup.okhttp.Callback
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import java.io.IOException

class StoresApi(listener: Listener) : ApiCall(listener) {

    companion object {

        private const val storeUrl = "http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/stores.json"

        private const val TAG = "StoresApi"
    }


    var request: Request


    var isInProgress = false

    init {
        request = Request.Builder()
            .url(storeUrl)
            .build()
    }


    fun callStores(context: Context) {

        if (!isNetworkAvailable(context)) {
            listener.onNoNetwork()
            return
        }

        if (!isInProgress) {

            okHttpClient.newCall(request).enqueue(object : Callback {

                override fun onFailure(request: Request?, e: IOException?) {

                    isInProgress = false
                    onFailureApi("")
                }

                override fun onResponse(response: Response?) {

                    isInProgress = false
                    val responseText = response?.body()?.string()
                    if (responseText != null && responseText.isNotEmpty()) {
                        onSuccessApi(responseText)
                    } else {
                        onFailureApi("")
                    }
                }
            })
        }
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

}