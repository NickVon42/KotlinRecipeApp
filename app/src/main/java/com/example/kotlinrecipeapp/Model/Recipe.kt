package com.example.kotlinrecipeapp.Model

import android.os.Parcel
import android.os.Parcelable

class Recipe (
    var id: String?,
    var type: String?,
    var title: String?,
    var prepTime: Int?,
    var ingredients: List<String>?,
    var directions: List<String>?,
    var image: String?
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(type)
        parcel.writeString(title)
        parcel.writeValue(prepTime)
        parcel.writeStringList(ingredients)
        parcel.writeStringList(directions)
        parcel.writeString(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}