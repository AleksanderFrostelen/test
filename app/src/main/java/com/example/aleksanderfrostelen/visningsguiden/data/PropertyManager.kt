package com.example.aleksanderfrostelen.visningsguiden.data

import android.content.Context
import java.lang.ref.WeakReference
import com.example.aleksanderfrostelen.visningsguiden.util.TinyDB


object PropertyManager {
    private var properties = mutableListOf<Property>()

    fun addProperty(property: Property) {
        properties.add(property)
        PropertyRepository.save(property)
    }

    fun fetchProperties(): List<Property>{
        if (properties.isEmpty()) getProperties()
        return properties
    }

    fun updateProperty(index: Int, property: Property) {
        properties[index] = property
        PropertyRepository.update(index, property)
    }

    fun getProperties() {
        properties = PropertyRepository.loadProperties()
    }

}



object PropertyRepository {
    var db: WeakReference<TinyDB>? = null


    fun setup(context: Context) {
        db = WeakReference(TinyDB(context))
    }

    fun save(property: Property){
        var saveData = db?.get()?.getListObject("Properties", Property::class.java)

        if (saveData != null) {
            saveData.add(property)
        } else {
            saveData = arrayListOf(property)
        }
        db?.get()?.putListObject("Properties", saveData)
    }

    fun update(index: Int, property: Property){
        var loadData = db?.get()?.getListObject("Properties", Property::class.java)

        if (loadData != null) {
            loadData.add(index, property)
        } else {
            loadData = arrayListOf(property)
        }
        db?.get()?.putListObject("Properties", loadData)

    }

    fun loadProperties(): MutableList<Property>{
        val result = db?.get()?.getListObject("Properties", Property::class.java)

        if (result != null) return result.map {it as Property}.toMutableList()
        else return mutableListOf()

    }
}