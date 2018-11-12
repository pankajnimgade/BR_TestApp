package com.rocket.bottle.testapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.ImageView
import android.widget.TextView
import com.rocket.bottle.testapp.R
import com.rocket.bottle.testapp.database.room.seed.Store
import com.squareup.picasso.Picasso

class StoreAdapter(
    private val storeList: List<Store>,
    private val listener: StoreListener
) : RecyclerView.Adapter<StoreAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val viewHolder = ViewHolder(layoutInflater.inflate(R.layout.single_store_item, viewGroup, false))

        viewHolder.itemView.setOnClickListener {
            val store = storeList[viewHolder.adapterPosition]
            listener.onStoreClick(store = store)
        }
        return viewHolder
    }

    override fun getItemCount(): Int {

        return storeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val store = this.storeList[position]

        holder.storeName.text = store.name
        holder.cityTv.text = store.city
        holder.addressTv.text = store.address
        holder.zipCodeTv.text = store.zipcode
        holder.phoneTv.text = store.phone

        if (URLUtil.isValidUrl(store.storeLogoURL)) {
            Picasso.get().load(store.storeLogoURL).into(holder.logoIv)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val storeName = itemView.findViewById<TextView>(R.id.store_name_tv)!!
        val logoIv = itemView.findViewById<ImageView>(R.id.store_log_iv)!!
        val cityTv = itemView.findViewById<TextView>(R.id.city_tv)!!
        val addressTv = itemView.findViewById<TextView>(R.id.address_tv)!!
        val zipCodeTv = itemView.findViewById<TextView>(R.id.zip_code_tv)!!
        val phoneTv = itemView.findViewById<TextView>(R.id.phone_tv)!!
    }
}

interface StoreListener {

    fun onStoreClick(store: Store)
}