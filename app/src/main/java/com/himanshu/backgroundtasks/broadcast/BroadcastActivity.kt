package com.himanshu.backgroundtasks.broadcast

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.himanshu.backgroundtasks.R
import com.himanshu.backgroundtasks.workmanager.NotifWorker

class BroadcastActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast)

        createChannel()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<NotifWorker>()
                .setConstraints(constraints)
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)

    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                "ChannelId",
                "ChannelName",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(true)
            }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Demo check"

            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(notificationChannel)

        }
    }
}