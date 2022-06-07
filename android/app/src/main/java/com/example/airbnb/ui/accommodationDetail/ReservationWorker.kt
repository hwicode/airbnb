package com.example.airbnb.ui.accommodationDetail

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReservationWorker (private val appContext: Context, workerParameters: WorkerParameters) :
    Worker(appContext, workerParameters) {
    override fun doWork(): Result {
        Thread.sleep(3000)
        val receiver = ReservationBroadCast()
        val filter = IntentFilter("MyAction")
        appContext.registerReceiver(receiver, filter)

        // 브로드케스트 발송
        Intent().also {
            it.setAction("MyAction")
                .setPackage("com.example.airbnb")
            appContext.sendBroadcast(it)
        }
        return Result.success()
    }

}