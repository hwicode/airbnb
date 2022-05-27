package com.example.airbnb.domain.model

data class CalendarDay (
    val month:Int,
    val day:String,
    var isStartDay:Boolean= false,
    var isSelectable:Boolean=true,
    var isSelected:Boolean=false,
    var isInRange:Boolean=false
)