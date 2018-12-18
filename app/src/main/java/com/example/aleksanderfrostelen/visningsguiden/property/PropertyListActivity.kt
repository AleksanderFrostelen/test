package com.example.aleksanderfrostelen.visningsguiden.property

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyManager
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyRepository
import kotlinx.android.synthetic.main.activity_property_list.*

class PropertyListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_list)

        PropertyRepository.setup(this)


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
}
