package com.example.airbnb.ui.common

import android.graphics.Color
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
    println(dateString)
    view.text = view.context.getString(R.string.checkout_date, dateString)
}

@BindingAdapter("bindDateString", "checkIn", "checkOut")
fun displayCheckOutDate(view: TextView, currentDateTime: LocalDate?, checkIn: LocalDate?, checkOut: LocalDate?) {
    checkOut?.let {
        checkIn?.let {
            currentDateTime?.let {
                if ((it <= checkOut) && (it >= checkIn)) {
                    view.setBackgroundColor(Color.GRAY)
                }
            }
        }
    }
}

@BindingAdapter("bindDateString")
fun displayDate(view: TextView, dateTime: LocalDate?) {
    dateTime?.let {
        val dateString = dateTime.toString("dd")
        view.text = dateString
    }
}