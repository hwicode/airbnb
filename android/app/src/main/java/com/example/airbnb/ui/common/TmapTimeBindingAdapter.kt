package com.example.airbnb.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.airbnb.R

@BindingAdapter("convertTime")
fun convertMinToHour(view: TextView, totalTime: Int?) {
    var time = ""
    totalTime?.let {
        time = when {
            it < 60 -> "${it}분"
            else -> String.format("%.1f", it/60.0) + "시간"
        }
    }
    view.text = view.context.getString(R.string.time_to_destination, time)

}

