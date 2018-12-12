package com.example.aleksanderfrostelen.visningsguiden.property.room

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.data.Room
import kotlinx.android.synthetic.main.room_view.view.*

class RoomAdapter(private val contents: List<Room>, private val propertyId: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_view, parent, false) as View

        return RoomViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RoomViewHolder).bind(contents[position])
    }

    inner class RoomViewHolder(var view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        override fun onClick(view: View?) {
            view?.let {
                val intent = Intent(view.context, RoomActivity::class.java)
                intent.putExtra("RoomId", this.adapterPosition)
                intent.putExtra("PropertyId", propertyId)
                startActivity(view.context, intent, null)

            }

        }

        init {
            view.visitRoom.setOnClickListener(this)
        }

        fun bind(room: Room) = with(view) {
            val questionsChecked = room.questions.filter { it.checked }.size
            val questionsTotal = room.questions.size
            val roomNote = room.questions.filter { it.note.isNotBlank() }.joinToString(", ") { it.note }


            view.roomTitle.text = room.title
            view.finishedTasks.text = "${questionsChecked}/${questionsTotal}"
            view.roomNote.text = roomNote


        }
    }

    override fun getItemCount() = contents.size
}