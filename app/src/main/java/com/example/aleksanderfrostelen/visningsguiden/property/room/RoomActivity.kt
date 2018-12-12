package com.example.aleksanderfrostelen.visningsguiden.property.room

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.EditText
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.data.PropertyManager
import com.example.aleksanderfrostelen.visningsguiden.data.QuestionAdapter
import kotlinx.android.synthetic.main.activity_room.*
import kotlinx.android.synthetic.main.content_room.*
import kotlinx.android.synthetic.main.question_view.*

class RoomActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: QuestionAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private var roomId: Int = -1
    private var propertyId: Int = -1
    // private var userNote: EditText = userNotesText
    // private var rating: AdvancedRadioGroup = roomRating
    //   lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)

        backButton.setOnClickListener(this)

        roomId = intent.getIntExtra("RoomId", -1)
        propertyId = intent.getIntExtra("PropertyId", -1)

    }

    override fun onResume() {
        super.onResume()
        val property = PropertyManager.fetchProperties().get(propertyId)
        val room = property.listOfRooms.get(roomId)
        val questionsTotal = room.questions.size

        roomTitle2.text = room.title
        totalQuestions.text = "${questionsTotal}"
        findViewById<AppCompatRadioButton>(getRatingCheckbox(room.userPoints))?.isChecked = true

        viewManager = LinearLayoutManager(this)
        viewAdapter = QuestionAdapter(room.questions.toMutableList())

        recyclerView = findViewById<RecyclerView>(R.id.content_room_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter


        }
         val preferencesHelper = SavingPreferences(this)
          val token = preferencesHelper.deviceToken


        //   userNote = findViewById<EditText>(R.id.userNotesText)
        // readInfo()

        doneButton.setOnClickListener {
            room.questions = viewAdapter.getContents()
            room.userPoints = roomRating.checkedItem?.text.toString().toInt()
            property.listOfRooms.set(roomId, room)
            PropertyManager.updateProperty(propertyId, property)
            // preferencesHelper.deviceToken = token
            // Log.v("TAG", "My token is: $token")

            //      saveInfo()
            finish()
        }
    }

     class SavingPreferences(context: Context){
        companion object {
            const val DEVELOP_MODE = false
            private const  val DEVICE_TOKEN = "data.source.prefs.DEVICE_TOKEN"
        }
        private val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        var deviceToken = preferences.getString(DEVICE_TOKEN, "")
        set(value) = preferences.edit().putString(DEVICE_TOKEN, value).apply()
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
          val userNotes = edit.getString("Note", "fail!")
          userNote.text = userNotes
      } */


    private fun getRatingCheckbox(rating: Int?): Int {
        return when (rating) {
            1 -> R.id.rating1
            2 -> R.id.rating2
            3 -> R.id.rating3
            4 -> R.id.rating4
            5 -> R.id.rating5
            else -> {
                R.id.rating1
            }
        }
    }


    override fun onClick(view: View?) {
        view?.let {
            when (it.id) {
                backButton.id -> {
                    finish()
                }

            }
        }
    }
}
