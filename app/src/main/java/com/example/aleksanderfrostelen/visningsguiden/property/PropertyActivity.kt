package com.example.aleksanderfrostelen.visningsguiden.property

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.data.Property
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyManager
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyRepository
import com.example.aleksanderfrostelen.visningsguiden.util.toFormattedNumber
import kotlinx.android.synthetic.main.activity_property.*
import kotlinx.android.synthetic.main.content_property.*

class PropertyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_property)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        PropertyRepository.setup(this)

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
            if(propertyCost.text.isEmpty()){
                propertyCost.error = "Please give a cost"
                hasErrors = true
            }

            if (!hasErrors) {

                val property = Property(
                    propertyAddress.text.toString(),
                    propertySqm.text.toString().toInt(),
                    propertyRoom.text.toString().toInt(),
                    storageYes.isChecked,
                    propertyDescription.text.toString(),
                    propertyCost.text.toString().toInt()

                )

                PropertyManager.addProperty(property)

                val intent = Intent(this, PropertyListActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
