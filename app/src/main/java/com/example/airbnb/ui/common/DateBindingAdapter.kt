package com.example.airbnb.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.airbnb.R
import org.joda.time.LocalDate

@BindingAdapter("checkInAsString")
fun displayCheckInDate(view: TextView, dateTime: LocalDate?) {
    val dateString = dateTime?.toString("MM월 dd일")
    view.text = dateString
}

@BindingAdapter("checkOutAsString")
fun displayCheckOutDate(view: TextView, dateTime: LocalDate?) {
    val dateString = dateTime?.toString("MM월 dd일") ?: ""
    view.text = view.context.getString(R.string.checkout_date, dateString)
}