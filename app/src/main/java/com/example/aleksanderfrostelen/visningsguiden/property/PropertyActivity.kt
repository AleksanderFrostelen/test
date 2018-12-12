package com.example.aleksanderfrostelen.visningsguiden.property

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.data.Property
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyManager
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.content_property.*

class PropertyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        readyButton.setOnClickListener {
            var hasErrors = false

            if (propertySqm.text.isEmpty()) {
                propertySqm.error = "Please give a size "
                hasErrors = true
            }

            if (propertyAddress.text.isEmpty()) {
                propertyAddress.error = "Please give an address"
                hasErrors = true
            }


            if (propertyRoom.text.isEmpty()) {
                propertyRoom.error = "Please give a room"
                hasErrors = true
            }

            if (!hasErrors) {

                // 1. Create a property model
                val property = Property(
                    propertyAddress.text.toString(),
                    propertySqm.text.toString().toInt(),
                    propertyRoom.text.toString().toInt(),
                    storageYes.isChecked,
                    propertyDescription.text.toString()
                )

                PropertyManager.addProperty(property)

                val intent = Intent(this, PropertyListActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
