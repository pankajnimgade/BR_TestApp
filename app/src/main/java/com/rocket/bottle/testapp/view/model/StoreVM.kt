package com.rocket.bottle.testapp.view.model

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.rocket.bottle.testapp.BottleRocket
import com.rocket.bottle.testapp.database.room.seed.Store

class StoreVM : ViewModel() {

    private val mStore: MutableLiveData<Store> = MutableLiveData()


    fun getStore(storeId: String): MutableLiveData<Store> {

        RetrieveStore(mStore).execute(storeId)

        return mStore
    }

}

class RetrieveStore(private val mStore: MutableLiveData<Store>) : AsyncTask<String, Void, Store>() {

    override fun doInBackground(vararg storeId: String): Store {

        val storeDoa = BottleRocket.getAppDatabase().storeDoa()
        return storeDoa.storeByIds(storeID = storeId[0])
    }

    override fun onPostExecute(result: Store?) {
        super.onPostExecute(result)

        result?.let {
            mStore.postValue(result)
        }
    }

}