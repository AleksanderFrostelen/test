package com.example.aleksanderfrostelen.visningsguiden.property.room

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText
import android.widget.Toast
import com.example.aleksanderfrostelen.visningsguiden.R
import kotlinx.android.synthetic.main.content_room.*
import kotlinx.android.synthetic.main.question_view.*

class UserData : AppCompatActivity() {

    private var userNote: EditText = userNotesText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.question_view)

        userNote = findViewById(R.id.userNotesText)
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)


        doneButton.setOnClickListener {
           // saveInfo()
            val editPref = getSharedPreferences("Editor", Context.MODE_PRIVATE)
            val editor = editPref.edit()
            editor.putString("note", userNote.getText().toString())
            editor.apply()
            Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
        }

       // readInfo()
    }


  /*  private fun saveInfo() {
        val edit = getSharedPreferences("Editor", Context.MODE_PRIVATE)
        val editSp = edit.edit()
        if (userNote.text.isEmpty()) {
            userNote.error = "Error"
        }
        editSp.putString("Note", userNote.text.toString())
        editSp.apply()
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show()
    }

    private fun readInfo() {
        val edit = getSharedPreferences("Editor", Context.MODE_PRIVATE)
        val userNotes = edit.getString("Note", "!!!")
        userNote.setText(userNotes)
    } */
}

