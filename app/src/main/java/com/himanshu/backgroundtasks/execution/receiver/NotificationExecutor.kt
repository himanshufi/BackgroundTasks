package com.himanshu.backgroundtasks.execution.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.himanshu.backgroundtasks.utils.Constants
import com.himanshu.backgroundtasks.R

class NotificationExecutor: BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent) {

        val isDataSaved = p1.getBooleanExtra(Constants.STATUS, false)

        val eggImage = BitmapFactory.decodeResource(
                p0.resources,
                R.mipmap.ic_launcher
        )
        val bigPicStyle = NotificationCompat.BigPictureStyle()
                .bigPicture(eggImage)
                .bigLargeIcon(null)

        val snoozeIntent = Intent(p0, TaskExecutor::class.java)
        val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
                p0,
                Constants.REQUEST_CODE,
                snoozeIntent,
                Constants.FLAGS)

        val builder = NotificationCompat.Builder(p0, p0.getString(R.string.channel_id))
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(if(isDataSaved) "Success" else "Failure")
                .setContentText("Your data save status is : ${if(isDataSaved) "Success" else "Failure"}")
                .setAutoCancel(true)
                .setStyle(bigPicStyle)
                .setLargeIcon(eggImage)
                .addAction(
                        R.mipmap.ic_launcher,
                        "Click her to do it again!!",
                        snoozePendingIntent
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)

        builder.build()

        val mNotificationManager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, builder.build())

    }

}