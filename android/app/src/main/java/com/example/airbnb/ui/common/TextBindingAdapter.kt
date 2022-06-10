package com.example.airbnb.ui.common

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.airbnb.domain.model.SearchCondition

@BindingAdapter("searchCondition")
fun displayAllCondition(view:TextView, condition: SearchCondition){
    view.text = "${condition.searchTag} * ${condition.checkInDate} - ${condition.checkOutDate} * ${condition.minPrice}-${condition.maxPrice}"
}