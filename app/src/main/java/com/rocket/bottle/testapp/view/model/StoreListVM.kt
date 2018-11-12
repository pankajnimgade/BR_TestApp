package com.rocket.bottle.testapp.view.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.google.gson.Gson
import com.rocket.bottle.testapp.BottleRocket
import com.rocket.bottle.testapp.activities.ActivityListener
import com.rocket.bottle.testapp.database.room.seed.Store
import com.rocket.bottle.testapp.database.room.seed.StoreResponse
import com.rocket.bottle.testapp.network.Listener
import com.rocket.bottle.testapp.network.StoresApi

class StoreListVM : ViewModel(), Listener {

    companion object {

        private val TAG = StoreListVM::class.java.simpleName
    }

    private lateinit var mActivityListener: ActivityListener

    val mStoresList: MutableLiveData<List<Store>> = MutableLiveData()

    private val mStoreApiCall: StoresApi = StoresApi(this)

    init {
        mStoresList.value = mutableListOf()
    }

    fun setView(viewListener: ActivityListener) {
        this.mActivityListener = viewListener
    }


    fun getStores(context: Context): MutableLiveData<List<Store>> {
        mStoreApiCall.callStores(context)
        return mStoresList
    }

    override fun onSuccess(response: String) {

        val storeResponse = (Gson()).fromJson(response, StoreResponse::class.java)

        // Saving to database
        val appDatabase = BottleRocket.getAppDatabase()
        storeResponse.stores.forEach { store -> appDatabase.storeDoa().insertAll(store) }

        // making it available for UI
        mStoresList.postValue(appDatabase.storeDoa().getAll())
    }

    override fun onFailure(response: String) {
        mActivityListener.onApiCallError()
    }

    override fun onNoNetwork() {
        mActivityListener.onNoNetwork()
    }
}