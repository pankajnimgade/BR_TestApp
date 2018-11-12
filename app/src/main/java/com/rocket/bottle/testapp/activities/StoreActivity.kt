package com.rocket.bottle.testapp.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.URLUtil
import com.rocket.bottle.testapp.R
import com.rocket.bottle.testapp.fragments.MapFragment
import com.rocket.bottle.testapp.view.model.StoreVM
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_store.*
import kotlinx.android.synthetic.main.content_store.*

/**
 * Detailed information about a store
 * */
class StoreActivity : AppCompatActivity() {

    companion object {

        private val TAG = StoreActivity::class.java.simpleName

        private const val STORE_ID_KEY = "STORE_ID_KEY"

        fun startWithStoreId(context: Context, storeId: String) {

            val intent = Intent(context, StoreActivity::class.java)
            intent.putExtra(STORE_ID_KEY, storeId)
            context.startActivity(intent)
        }
    }

    private lateinit var storeVM: StoreVM


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initializeUi()

    }

    private fun initializeUi() {
        storeVM = ViewModelProviders.of(this).get(StoreVM::class.java)
        val storeId = intent.getStringExtra(STORE_ID_KEY)
        storeVM.getStore(storeId).observe(this, Observer { store ->

            toolbar.title = store?.name

            if (URLUtil.isValidUrl(store?.storeLogoURL)) {
                Picasso.get().load(store?.storeLogoURL).into(store_log_iv)
            }

            address_tv.text = store?.address
            city_tv.text = store?.city
            zip_code_tv.text = store?.zipcode
            state_tv.text = store?.state
            phone_tv.text = "Contact: ${store?.phone}"

            val fragment = MapFragment.newInstance(store?.name!!, store.latitude!!, store.longitude!!)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.map_container, fragment).commit()
        })

    }

}
