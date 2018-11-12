package com.rocket.bottle.testapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
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
                val adapter = StoreAdapter(storeList, this)
                recycler_view.adapter = adapter
            }
        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onStoreClick(store: Store) {
        Log.d(TAG, ": ${store.name}")
    }

/*    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_address_list, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/
}
