package com.example.airbnb.ui.common

import android.app.ActivityManager
import android.content.Context
import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.example.airbnb.R
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textfield.TextInputLayout.END_ICON_CLEAR_TEXT
import com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM

fun TextInputLayout.switchFromClearTextToCustomMode(context: Context) {
    this.endIconMode = END_ICON_CUSTOM
    this.setEndIconDrawable(R.drawable.ic_search)
    this.setEndIconTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.pink)))
}

fun TextInputLayout.switchFromCustomModeToClearText(context: Context) {
    this.endIconMode = END_ICON_CLEAR_TEXT
    this.setEndIconDrawable(R.drawable.ic_clear)
    this.setEndIconTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.grey1)))
}

fun isAppInForegrounded(): Boolean {
    val appProcessInfo = ActivityManager.RunningAppProcessInfo();
    ActivityManager.getMyMemoryState(appProcessInfo);
    return (appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND ||
            appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE)
}