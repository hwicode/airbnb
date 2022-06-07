package com.example.airbnb.ui.accommodationDetail

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.airbnb.ui.common.Notification
import com.example.airbnb.ui.common.isAppInForegrounded

class ReservationBroadCast : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (isAppInForegrounded()) {
            Toast.makeText(
                context,
                "실행중 처리",
                Toast.LENGTH_SHORT
            ).show()

        } else {
            val notificationHelper= Notification(context)
            val builder = Notification(context).getOrderChannelNotification("예약", "예약 승인되었습니다")
            notificationHelper.getManger().notify(101, builder.build())
        }
    }
}
