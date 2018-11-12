package com.rocket.bottle.testapp.standalone

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rocket.bottle.testapp.activities.StoreListActivity
import com.rocket.bottle.testapp.R
import com.rocket.bottle.testapp.activities.SplashActivity

class Screen(val name: String, val activityName: Class<out Activity>)

interface ScreenClickListener {
    fun onScreenClick(screen: Screen)
}

class ScreenUtils {

    companion object {

        fun getScreenList(): List<Screen> {

            val list = mutableListOf<Screen>()
            list.add(Screen("Splash", SplashActivity::class.java))
            list.add(Screen("Store List", StoreListActivity::class.java))
            return list
        }
    }
}

class ScreenAdapter(private val screenList: List<Screen>, private val listener: ScreenClickListener) :
    RecyclerView.Adapter<ScreenAdapter.ViewHolder>() {


    override fun onCreateViewHolder(viewHolder: ViewGroup, p1: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(viewHolder.context)
        val view = ViewHolder(layoutInflater.inflate(R.layout.single_screen_item, viewHolder, false))
        view.itemView.setOnClickListener {
            val screen = screenList[view.adapterPosition]
            listener.onScreenClick(screen)
        }
        return view
    }

    override fun getItemCount(): Int {
        return screenList.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val screen = screenList[position]
        viewHolder.nameTx.text = screen.name
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTx = itemView.findViewById<TextView>(R.id.title)!!
    }
}