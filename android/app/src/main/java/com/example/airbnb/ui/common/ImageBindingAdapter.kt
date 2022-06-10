package com.example.airbnb.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.airbnb.common.GlideApp


@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUri: String?) {
    if (!imageUri.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUri)
            .into(view)



    }

}