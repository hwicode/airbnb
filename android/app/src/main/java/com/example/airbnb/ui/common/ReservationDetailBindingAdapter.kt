package com.example.airbnb.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.airbnb.R
import com.example.airbnb.data.dto.AccommodationOptionLine
import org.joda.time.Days
import org.joda.time.LocalTime
import org.joda.time.format.DateTimeFormat
import java.text.DecimalFormat

private val formatter = DecimalFormat("#,###")

private fun convertStringToDateFormat(date: String) = LocalTime.parse(date).toString("MM월 dd일")

@BindingAdapter("bindStringToDateFormat")
fun bindStringToDateFormat(view: TextView, date: String?) {
    date?.let {
        val dateFormat = convertStringToDateFormat(it)
        view.text = dateFormat
    }
}

@BindingAdapter("bindAdultNumber", "bindChildCount")
fun bindGuestNumber(view: TextView, adultCount: Int?, childCount: Int?) {
    view.text = "${adultCount ?: 0} + ${childCount ?: 0} 명"
}

@BindingAdapter("bindCostToString")
fun bindCostToString(view: TextView, cost: Int?) {
    view.text = formatter.format(cost)
}

@BindingAdapter("bindCleanCostToString")
fun bindCleanCostToString(view: TextView, cost: List<AccommodationOptionLine>?) {
    view.text = cost?.get(0)?.let { formatter.format(it.price) }
}

@BindingAdapter("bindOnlyOneDayPrice", "bindCheckInDate", "bindCheckOutDate")
fun bindAccommodationPrice(view: TextView, price: Int, checkIn: String, checkOut: String) {
    val priceToString = formatter.format(price)
    val betweenDays = convertStringToDate(checkIn, checkOut)
    view.text =
        view.context.getString(R.string.reservation_accommodation_price, priceToString, betweenDays)
}

@BindingAdapter("bindOnlyOneDayPriceValue", "bindCheckInDateValue", "bindCheckOutDateValue")
fun bindAccommodationPriceValue(view: TextView, price: Int, checkIn: String, checkOut: String) {
    val totalPrice = getTotalPrice(checkIn, checkOut, price)
    view.text = "₩${formatter.format(totalPrice)}"
}

private fun convertStringToDate(
    checkIn: String,
    checkOut: String
): Int {
    val formatter = DateTimeFormat.forPattern("yyyy-MM-dd")
    val checkInDate = formatter.parseLocalDate(checkIn)
    val checkOutDate = formatter.parseLocalDate(checkOut)
    val betweenDays = (Days.daysBetween(checkInDate, checkOutDate).days)
    return betweenDays
}

@BindingAdapter(
    "bindOnlyOneDayPriceDiscount",
    "bindCheckInDateValueDiscount",
    "bindCheckOutDateValueDiscount"
)
fun bindDiscount(view: TextView, price: Int, checkIn: String, checkOut: String) {
    val totalPrice = getTotalPrice(checkIn, checkOut, price)
    val discount = (totalPrice * 0.04).toInt()
    view.text = "-₩${formatter.format(discount)}"
}

private fun getTotalPrice(
    checkIn: String,
    checkOut: String,
    price: Int
): Int {
    val betweenDays = convertStringToDate(checkIn, checkOut)
    val totalPrice = price * betweenDays
    return totalPrice
}

@BindingAdapter(
    "bindOnlyOneDayPriceServiceFee",
    "bindCheckInDateServiceFee",
    "bindCheckOutDateServiceFee"
)
fun bindServiceFee(view: TextView, price: Int, checkIn: String, checkOut: String) {
    val totalPrice = getTotalPrice(checkIn, checkOut, price)
    val serviceFee = (totalPrice * 0.0065).toInt()
    view.text = "₩${formatter.format(serviceFee)}"
}

@BindingAdapter("bindOnlyOneDayPriceTax", "bindCheckInDateTax", "bindCheckOutDateTax")
fun bindTax(view: TextView, price: Int, checkIn: String, checkOut: String) {
    val totalPrice = getTotalPrice(checkIn, checkOut, price)
    val tax = (totalPrice * 0.0065 * 0.1).toInt()
    view.text = "₩${formatter.format(tax)}"
}

@BindingAdapter(
    "bindOnlyOneDayPriceToTal",
    "bindCheckInDateToTal",
    "bindCheckOutDateToTal",
    "bindCleanCost"
)
fun bindTotalPrice(
    view: TextView,
    price: Int,
    checkIn: String,
    checkOut: String,
    cost: List<AccommodationOptionLine>?
) {
    val price = getTotalPrice(checkIn, checkOut, price)
    val discount = (price * 0.04).toInt()
    val serviceFee = (price * 0.0065).toInt()
    val cleanCost = cost?.get(0)?.price?.toInt() ?: 0
    val tax = (discount * 0.1).toInt()
    val totalPrice = price - discount + cleanCost + serviceFee + tax
    view.text = "₩${formatter.format(totalPrice)}"
}