package com.example.airbnb.domain.model

import android.os.Parcel
import android.os.Parcelable

data class SearchCondition(
    val searchTag: String,
    val checkInDate: String = "",
    val checkOutDate: String = "",
    val minPrice: Int = 0,
    val maxPrice: Int = 0,
    val adultCount: Int = 0,
    val childCount: Int = 0,
    val toddlerCount: Int = 0
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(searchTag)
        parcel.writeString(checkInDate)
        parcel.writeString(checkOutDate)
        parcel.writeInt(minPrice)
        parcel.writeInt(maxPrice)
        parcel.writeInt(adultCount)
        parcel.writeInt(childCount)
        parcel.writeInt(toddlerCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SearchCondition> {
        override fun createFromParcel(parcel: Parcel): SearchCondition {
            return SearchCondition(parcel)
        }

        override fun newArray(size: Int): Array<SearchCondition?> {
            return arrayOfNulls(size)
        }
    }
}


fun SearchCondition.toQueryMap(): MutableMap<String, String> {
    val queryMap : MutableMap<String, String> = mutableMapOf()
    queryMap["checkIn"]= checkInDate
    queryMap["checkOut"]= checkOutDate
    queryMap["minPrice"]= minPrice.toString()
    queryMap["maxPrice"]= maxPrice.toString()
    queryMap["adults"] = adultCount.toString()
    queryMap["children"] = childCount.toString()
    queryMap["infants"] = toddlerCount.toString()
    queryMap["page"] = "0"
    return queryMap
}