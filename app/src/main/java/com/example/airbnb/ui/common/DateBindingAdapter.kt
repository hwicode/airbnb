package com.example.airbnb.ui.common

import android.graphics.Color
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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
    view.text = ""
    dateTime?.let {
        val dateString = dateTime.toString("MM월 dd일")
        view.text = view.context.getString(R.string.checkout_date, dateString)
    }
}

@BindingAdapter("bindDateString", "checkIn", "checkOut")
fun displayCheckOutDate(
    view: ConstraintLayout,
    currentDateTime: LocalDate?,
    checkIn: LocalDate?,
    checkOut: LocalDate?
) {
    currentDateTime?.let {
        if (checkIn != null && checkOut != null) {
            if ((it < checkOut) && (it > checkIn)) {
                view.setBackgroundColor(Color.GRAY)
            } else if (it == checkIn || it == checkOut) {
                view.setBackgroundColor(Color.BLUE)
            } else {
                view.setBackgroundColor(Color.WHITE)
            }
        } else if (checkIn != null && checkOut == null) {
            if (it.isEqual(checkIn)) {
                view.setBackgroundColor(Color.BLUE)
            } else {
                view.setBackgroundColor(Color.WHITE)
            }
        } else {
            view.setBackgroundColor(Color.WHITE)
        }
    } ?: view.setBackgroundColor(Color.WHITE)
}
