package com.example.aleksanderfrostelen.visningsguiden.model

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.View
import com.example.aleksanderfrostelen.visningsguiden.property.PropertyActivity
import com.example.aleksanderfrostelen.visningsguiden.R
import com.google.android.gms.maps.model.LatLng

import kotlinx.android.synthetic.main.activity_property_model.*
import kotlinx.android.synthetic.main.content_property.*

class PropertyModel : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property_model)
        setSupportActionBar(toolbar)
        readyButton.setOnClickListener(this)

        readyButton.setOnClickListener{
            val intent = Intent(this@PropertyModel, PropertyActivity::class.java)
            val address = propertyAddress.text
            val room = propertyRoom.text
            val size = propertySqm.text.toString().toInt()
            val description = propertyDescription.text
            val cost = propertyCost.text.toString().toInt()
            intent.putExtra("", address)
            intent.putExtra("", room)
            intent.putExtra("", size)
            intent.putExtra("", description)
            intent.putExtra("", cost)

            startActivity(intent)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onClick(view: View?){
        view?.let {
            when(it.id){
                readyButton.id ->{
                    finish()
                }
            }
        }
    }

}


