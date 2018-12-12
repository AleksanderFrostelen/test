package com.example.aleksanderfrostelen.visningsguiden.data

object PropertyManager {
    private val properties = mutableListOf(Property("Bla vägen", 23, 5, true, "A test description"))

    fun addProperty(property: Property) {
        properties.add(property)
    }

    fun fetchProperties(): List<Property>{
        return properties
    }

    fun updateProperty(index: Int, property: Property) {
        properties[index] = property
    }

}