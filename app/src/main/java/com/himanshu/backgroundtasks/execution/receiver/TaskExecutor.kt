package com.himanshu.backgroundtasks.execution.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import androidx.work.*
import com.himanshu.backgroundtasks.execution.workmanager.NotifWorker

class TaskExecutor: BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent?) {

        val notificationManager = ContextCompat.getSystemService(
                p0,
                NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelAll()

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<NotifWorker>()
                .setConstraints(constraints)
                .build()

        WorkManager
            .getInstance(p0)
            .enqueue(uploadWorkRequest)
    }

}