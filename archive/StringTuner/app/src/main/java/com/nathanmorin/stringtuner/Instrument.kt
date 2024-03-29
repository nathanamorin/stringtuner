package com.nathanmorin.stringtuner

import android.os.Parcel
import android.os.Parcelable

class Instrument(val id: Int, val name: String, val tuning: List<String>) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.createStringArrayList())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeStringList(tuning)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Instrument> {
        override fun createFromParcel(parcel: Parcel): Instrument {
            return Instrument(parcel)
        }

        override fun newArray(size: Int): Array<Instrument?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return name
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        if (other?.javaClass != javaClass) return false

        other as Instrument

        return other.name == name
    }


    fun matchesSearch(search: String?): Boolean {
        if (search.isNullOrEmpty()) return true
        search as String
        return this.name.toLowerCase().contains(search.toLowerCase())
    }
}