package com.himanshu.backgroundtasks.execution.receiver

import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.text.format.DateUtils
import androidx.core.app.AlarmManagerCompat
import androidx.core.app.NotificationCompat
import com.himanshu.backgroundtasks.App
import com.himanshu.backgroundtasks.utils.Constants
import com.himanshu.backgroundtasks.R
import com.himanshu.backgroundtasks.execution.async.CustomThread

class NotificationExecutor: BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent) {

        val isDataSaved = p1.getBooleanExtra(Constants.STATUS, false)

        val image = BitmapFactory.decodeResource(
                p0.resources,
                R.mipmap.ic_launcher
        )

        val builder = NotificationCompat.Builder(p0, p0.getString(R.string.channel_id))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(if(isDataSaved) "Success" else "Failure")
                .setContentText("Your data save status is : ${if(isDataSaved) "Success" else "Failure"}")
                .setAutoCancel(true)
                .setLargeIcon(image)
                .setPriority(NotificationCompat.PRIORITY_HIGH)

        builder.build()

        val mNotificationManager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, builder.build())

        val notifyIntent = Intent(p0, NotificationExecutor::class.java)
        notifyIntent.putExtra(Constants.STATUS, isDataSaved)
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
            SystemClock.elapsedRealtime() + (DateUtils.SECOND_IN_MILLIS * 5),
            notifyPendingIntent
        )

//        val pendingResult = goAsync()
        App.handler.post(CustomThread())

    }

}