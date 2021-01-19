package com.himanshu.backgroundtasks.workmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.text.format.DateUtils
import androidx.core.app.AlarmManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.himanshu.backgroundtasks.broadcast.receiver.BackgroundTaskReceiver

class NotifWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {

    override fun doWork(): Result {

        val notifyIntent = Intent(applicationContext, BackgroundTaskReceiver::class.java)
        val alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val notifyPendingIntent = PendingIntent.getBroadcast(
            applicationContext,
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


        return Result.success()
    }


}