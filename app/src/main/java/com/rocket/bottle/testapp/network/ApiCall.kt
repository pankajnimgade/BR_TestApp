package com.rocket.bottle.testapp.network

import com.squareup.okhttp.OkHttpClient

abstract class ApiCall(protected val listener: Listener) {

    companion object {

        private const val TAG = "ApiCall"
    }

    protected val okHttpClient: OkHttpClient = OkHttpClient()

    fun onSuccessApi(response: String) {
        listener.onSuccess(response)
    }

    fun onFailureApi(error: String) {
        listener.onFailure(error)
    }

    fun onNoNetworkApi() {
        listener.onNoNetwork()
    }

}

interface Listener {

    fun onSuccess(response: String)

    fun onFailure(response: String)

    fun onNoNetwork()

}