package com.rocket.bottle.testapp.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.rocket.bottle.testapp.R
import kotlinx.android.synthetic.main.fragment_map.*


class MapFragment : Fragment(), OnMapReadyCallback {

    lateinit var storeName: String
    lateinit var storeLatitude: String
    lateinit var storeLongitude: String

    companion object {

        private const val STORE_NAME_KEY = "STORE_NAME_KEY"
        private const val LATITUDE_KEY = "LATITUDE_KEY"
        private const val LONGITUDE_KEY = "LONGITUDE_KEY"

        fun newInstance(storeName: String, latitude: String, longitude: String): MapFragment {

            val fragment = MapFragment()
            fragment.storeName = storeName
            fragment.storeLatitude = latitude
            fragment.storeLongitude = longitude
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let {
            storeName = savedInstanceState.getString(STORE_NAME_KEY, "Macy's")
            storeLatitude = savedInstanceState.getString(LATITUDE_KEY, "28.068052")
            storeLongitude = savedInstanceState.getString(LONGITUDE_KEY, "-82.573301")
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        mapView.getMapAsync(this)
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)

        outState.putString(STORE_NAME_KEY, storeName)
        outState.putString(LATITUDE_KEY, storeLatitude)
        outState.putString(LONGITUDE_KEY, storeLongitude)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


    override fun onMapReady(googleMap: GoogleMap?) {

        val latitude = storeLatitude.toDouble()
        val longitude = storeLongitude.toDouble()
        val latLng = LatLng(latitude, longitude)
        val cameraPosition = CameraPosition.Builder()
            .target(latLng)
            .bearing(45.toFloat())
            .zoom(17.toFloat())
            .tilt(45.toFloat())
            .build()
        googleMap?.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        val markerOptions = MarkerOptions().position(latLng).title(storeName)
        googleMap?.addMarker(markerOptions)
    }


}
