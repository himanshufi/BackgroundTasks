package com.himanshu.backgroundtasks.broadcast.receiver

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.himanshu.backgroundtasks.R

// Notification ID.
private const val REQUEST_CODE = 0
private const val FLAGS = 0
class BackgroundTaskReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent) {
        Toast.makeText(p0, "Worked", Toast.LENGTH_SHORT).show()

        val eggImage = BitmapFactory.decodeResource(
                p0.resources,
                R.mipmap.ic_launcher
        )
        val bigPicStyle = NotificationCompat.BigPictureStyle()
                .bigPicture(eggImage)
                .bigLargeIcon(null)

        val snoozeIntent = Intent(p0, SnoozeReceiver::class.java)
        val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
                p0,
                REQUEST_CODE,
                snoozeIntent,
                FLAGS)

        val builder = NotificationCompat.Builder(p0, "ChannelId")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Custom Notification")
                .setContentText("Worked with Alarm Manager and Work Manager.")
                .setAutoCancel(true)
                .setStyle(bigPicStyle)
                .setLargeIcon(eggImage)
                .addAction(
                        R.mipmap.ic_launcher,
                        "Remind me after 1 minute",
                        snoozePendingIntent
                )
                .setPriority(NotificationCompat.PRIORITY_HIGH)

        builder.build()

        val mNotificationManager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, builder.build())

    }

}