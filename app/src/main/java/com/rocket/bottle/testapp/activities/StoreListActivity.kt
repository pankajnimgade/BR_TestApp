package com.rocket.bottle.testapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.rocket.bottle.testapp.R
import com.rocket.bottle.testapp.adapters.StoreAdapter
import com.rocket.bottle.testapp.adapters.StoreListener
import com.rocket.bottle.testapp.database.room.seed.Store
import com.rocket.bottle.testapp.view.model.StoreListVM
import kotlinx.android.synthetic.main.activity_store_list.*
import kotlinx.android.synthetic.main.content_store_list.*

class StoreListActivity : RootActivity(), StoreListener {

    companion object {
        private val TAG = StoreListActivity::class.java.simpleName
    }

    private lateinit var storeListVM: StoreListVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_list)
        setSupportActionBar(toolbar)
        rootView = root_view_CL

        storeListVM = ViewModelProviders.of(this).get(StoreListVM::class.java)
        storeListVM.setView(this)
        storeListVM.getStores(baseContext)

        storeListVM.mStoresList.observe(this, Observer { storeList ->

            storeList?.let {
                if (storeList.isNotEmpty()) {

                    loading_pb.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                    val adapter = StoreAdapter(storeList, this)
                    recycler_view.adapter = adapter
                }
            }
        })

    }

    override fun onStoreClick(store: Store) {
        StoreActivity.startWithStoreId(this, store.storeID!!)
    }

}
