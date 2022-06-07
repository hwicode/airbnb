package com.example.airbnb.ui.common

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.airbnb.R

const val channelID="reservationChannel"
const val channelName = "Notification Channel"
const val channelDesc = "Reservation Notification Channel"

class Notification(base:Context) : ContextWrapper(base) {

    private var manager:NotificationManager? = null

    init {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            createChannel()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(){
        val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = channelDesc
        getManger().createNotificationChannel(channel)
    }

    fun getManger():NotificationManager{
        if(manager==null){
            manager= getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return manager!!
    }

    fun getOrderChannelNotification(title:String, message: String):NotificationCompat.Builder{
        return NotificationCompat.Builder(applicationContext, channelID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_check)
    }
}