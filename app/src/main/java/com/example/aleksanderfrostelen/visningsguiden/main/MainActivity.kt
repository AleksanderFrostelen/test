package com.example.aleksanderfrostelen.visningsguiden.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.property.PropertyActivity
import com.example.aleksanderfrostelen.visningsguiden.property.PropertyListActivity

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)


        getStartedButton.setOnClickListener{
           val intent = Intent(this, PropertyActivity::class.java)
            startActivity(intent)
        }

        skipButton.setOnClickListener {
            val intent = Intent(this, PropertyListActivity::class.java)
            startActivity(intent)
        }
    }


}
