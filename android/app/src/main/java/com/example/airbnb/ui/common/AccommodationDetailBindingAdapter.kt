package com.example.airbnb.ui.common

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.example.airbnb.R
import java.text.DecimalFormat


@BindingAdapter("bindViewPagerCurrentPage", "bindViewPagerTap")
fun bindViewPagerTapNumber(view: TextView, currentPage: Int?, list: List<String>?) {
    view.text = view.context.getString(R.string.accommodation_detail_view_pager_number, currentPage, list?.size)
}

@BindingAdapter("bindRatings", "bindComments")
fun bindRating(view: TextView, averageRating: Double?, commentSize: Int?) {
    val ratingString = averageRating.toString()
    view.text = view.context.getString(R.string.accommodation_detail_rating_comments, ratingString, commentSize)
}

@BindingAdapter("bindHostName")
fun bindHostName(view: TextView, host: String?) {
    view.text = view.context.getString(R.string.accommodation_detail_host, host)
}

@BindingAdapter("bindMaxPeople", "bindBedRooms", "bindBeds", "bindBathRooms")
fun bindRooms(view: TextView, maxNumberOfPeople: Int?, bedRooms: Int?, beds: Int?, bathRooms: Int?) {
    view.text = view.context.getString(R.string.accommodation_detail_rooms_detail, maxNumberOfPeople, bedRooms, beds, bathRooms)
}

@BindingAdapter("bindOneDayPrice")
fun bindOneDayPrice(view: TextView, oneDayPerPrice: Int?) {
    val formatter = DecimalFormat("#,###")
    val price = formatter.format(oneDayPerPrice)
    view.text = view.context.getString(R.string.accommodation_detail_one_day_price, price)
}

@BindingAdapter("bindReservationCheckIn", "bindReservationCheckOut")
fun bindCheckInCheckOut(view: TextView, checkIn: String?, checkOut: String?) {
    view.text = view.context.getString(R.string.accommodation_detail_checkin_checkout, checkIn, checkOut)
}

@BindingAdapter("showLayout", "isReservationLayout")
fun showLayout(view: ConstraintLayout, checkOut: String?, isReservationLayout: Boolean?) {
    if (checkOut != null && isReservationLayout != null) {
        if (isReservationLayout) {
            view.isVisible = checkOut.isNotEmpty() && checkOut.isNotBlank()
        } else {
            view.isVisible = !(checkOut.isNotEmpty() && checkOut.isNotBlank())
        }
    }
}



