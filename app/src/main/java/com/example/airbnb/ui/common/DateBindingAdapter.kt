package com.example.airbnb.ui.common

import android.graphics.Color
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.example.airbnb.R
import com.example.airbnb.common.Constants
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
fun displayCheckOutDate(view: ConstraintLayout, currentDateTime: LocalDate?, checkIn: LocalDate?, checkOut: LocalDate?) {
    currentDateTime?.let {
        if (checkIn != null && checkOut != null) {
            if ((it < checkOut) && (it > checkIn)) {
                view.setBackgroundColor(Constants.CALENDAR_IN_RANGE_COLOR)
            } else if (it == checkIn || it == checkOut) {
                view.setBackgroundResource(R.drawable.layout_circle_grey_background)
            } else {
                view.setBackgroundColor(Color.WHITE)
            }
        } else if (checkIn != null && checkOut == null) {
            if (it.isEqual(checkIn)) {
                view.setBackgroundResource(R.drawable.layout_circle_grey_background)
            } else {
                view.setBackgroundColor(Color.WHITE)
            }
        } else {
            view.setBackgroundColor(Color.WHITE)
        }
    } ?: view.setBackgroundColor(Color.WHITE)
}
