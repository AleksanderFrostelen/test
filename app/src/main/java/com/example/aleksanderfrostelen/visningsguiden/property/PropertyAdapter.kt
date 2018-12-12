package com.example.aleksanderfrostelen.visningsguiden.property

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aleksanderfrostelen.visningsguiden.R
import com.example.aleksanderfrostelen.visningsguiden.data.Property
import com.example.aleksanderfrostelen.visningsguiden.property.room.RoomListActivity
import kotlinx.android.synthetic.main.property_view.view.*

class PropertyAdapter(private val contents: List<Property>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.property_view, parent, false) as View

        return PropertyViewHolder(view)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as PropertyViewHolder).bind(contents[position])
    }

    inner class PropertyViewHolder(var view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        override fun onClick(view: View?) {
            view?.let {
                val intent = Intent(view.context, RoomListActivity::class.java)
                intent.putExtra("PropertyID", this.adapterPosition)
                startActivity(view.context, intent, null)
            }
        }

        private val address = view.address
        private val area = view.area
        private val rooms = view.room


        init {
            view.visning.setOnClickListener(this)
        }

        fun bind(savedProperty: Property) = with(view) {
            address.setText(savedProperty.address)
            area.setText(savedProperty.livingArea.toString())
            rooms.setText(savedProperty.rooms.toString())

        }

    }

    override fun getItemCount() = contents.size

}