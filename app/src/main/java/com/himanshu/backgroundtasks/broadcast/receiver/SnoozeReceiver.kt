package com.himanshu.backgroundtasks.broadcast.receiver

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.text.format.DateUtils
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.work.*
import com.himanshu.backgroundtasks.workmanager.NotifWorker

class SnoozeReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent?) {

        val notificationManager = ContextCompat.getSystemService(
                p0,
                NotificationManager::class.java
        ) as NotificationManager
        notificationManager.cancelAll()

        val notifyIntent = Intent(p0, BackgroundTaskReceiver::class.java)
        val alarmManager = p0.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val notifyPendingIntent = PendingIntent.getBroadcast(
                p0,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        AlarmManagerCompat.setExactAndAllowWhileIdle(
                alarmManager,
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + DateUtils.MINUTE_IN_MILLIS,
                notifyPendingIntent
        )
    }

}