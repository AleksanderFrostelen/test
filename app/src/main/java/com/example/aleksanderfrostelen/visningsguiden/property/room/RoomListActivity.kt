package com.example.aleksanderfrostelen.visningsguiden.property.room

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyManager
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyRepository
import kotlinx.android.synthetic.main.activity_check_list.*
import kotlinx.android.synthetic.main.content_check_list.*
import kotlinx.android.synthetic.main.question_view.*

class RoomListActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var propertyId = -1



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        PropertyRepository.setup(this)
        setContentView(R.layout.activity_check_list)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        homeButton.setOnClickListener(this)

        propertyId = intent.getIntExtra("PropertyID", -1)

    }

    override fun onResume() {
        super.onResume()

        if (propertyId == -1) finish()
        else {
            val property = PropertyManager.fetchProperties()[propertyId]
            streetAddress.text = property.address
            roomCount.text = property.rooms.toString()
            propertySize.text = property.livingArea.toString()

            viewManager = LinearLayoutManager(this)
            viewAdapter = RoomAdapter(
                property.listOfRooms,
                propertyId
            )

            recyclerView = findViewById<RecyclerView>(R.id.room_recycler_view).apply {

                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter


            }
        }
    }


    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                homeButton.id -> {
                    finish()
                }
            }
        }
    }
}
