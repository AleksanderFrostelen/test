package com.example.aleksanderfrostelen.visningsguiden.property

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyManager
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_property_list.*

class PropertyListActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_list)


        viewManager = LinearLayoutManager(this)
        viewAdapter =
                PropertyAdapter(PropertyManager.fetchProperties())


        addProperty.setOnClickListener {
            val intent = Intent(this, PropertyActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById<RecyclerView>(R.id.property_recycler_view).apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewAdapter

        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.setAllGesturesEnabled(false)

        val midsommar = LatLng(59.301142, 18.010749)

        mMap.addMarker(MarkerOptions().position(midsommar).title("Marker in Candyland"))

        val cameraUpdate = CameraUpdateFactory.newLatLngZoom(midsommar, 16.0F)
        mMap.moveCamera(cameraUpdate)
    }
}
