package com.himanshu.backgroundtasks.broadcast.receiver

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.himanshu.backgroundtasks.R

private const val TAG = "BackgroundTaskReceiver"
class BackgroundTaskReceiver: BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent) {

        p1.action.let {

        }

        Log.v(TAG, "BackgroundTaskReceiver onReceive !!")

        Toast.makeText(p0, "Worked", Toast.LENGTH_SHORT).show()

        val mBuilder = NotificationCompat.Builder(p0, "ChannelId")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("Title")
            .setContentText("Description")
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        mBuilder.build()

        val mNotificationManager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager.notify(1, mBuilder.build())

    }

}