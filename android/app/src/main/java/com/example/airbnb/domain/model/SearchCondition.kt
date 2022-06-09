package com.example.airbnb.domain.model

data class SearchCondition(
    val searchTag:String,
    val checkInDate:String="",
    val checkOutDate:String="",
    val minPrice:Int= 0,
    val maxPrice:Int =0,
    val adultCount:Int=0,
    val childCount:Int=0,
    val toddlerCount:Int=0
)


fun SearchCondition.toQueryMap(): MutableMap<String, String> {
    val queryMap : MutableMap<String, String> = mutableMapOf()
    queryMap["tagName"]= searchTag
    queryMap["checkIn"]= checkInDate
    queryMap["checkOut"]= checkOutDate
    queryMap["minPrice"]= minPrice.toString()
    queryMap["maxPrice"]= maxPrice.toString()
    queryMap["adults"] = adultCount.toString()
    queryMap["children"]= childCount.toString()
    queryMap["infants"] = toddlerCount.toString()
    queryMap["page"] = "0"
    return queryMap
}