@file:Suppress("KDocUnresolvedReference")
package com.himanshu.backgroundtasks

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.text.format.DateUtils
import androidx.activity.viewModels
import androidx.core.app.AlarmManagerCompat
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.himanshu.backgroundtasks.databinding.ActivityMainBinding
import com.himanshu.backgroundtasks.execution.receiver.NotificationExecutor
import com.himanshu.backgroundtasks.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        createChannel()
        observeItemsCount()
        scheduleTask()
    }

    /**
     *
     * Let's observe the number of [PostModel] items in present in the db.
     * We will get a callback as soon as db is updated with the data after
     * which we will update the [TextView] with the count.
     *
     */

    private fun observeItemsCount() {
        viewModel.observePostsInDb().observe(this) {
            val text = "Number of post present in DB : ${it.size}"
            binding.textView.text = text
        }
    }

    /**
     *
     * We will schedule the task with the [WorkManager]
     * with one constraint i.e [NetworkType.UNMETERED]
     * which means work will be executed only when WIFi
     * is available.
     *
     * With [setInitialDelay] we will start the [Work]
     * after 1 minute so that we can test the approach
     * by closing the application.
     *
     */

    private fun scheduleTask() {
        val notifyIntent = Intent(applicationContext, NotificationExecutor::class.java)
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
            SystemClock.elapsedRealtime() + (DateUtils.SECOND_IN_MILLIS * 5),
            notifyPendingIntent
        )
    }

    /**
     *
     * To display a notification on the devices which are operating
     * above Android.O, we need to create a [NotificationChannel].
     * With which we can also customize the notification.
     *
     */

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                getString(R.string.channel_id),
                getString(R.string.channel_name),
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                setShowBadge(true)
            }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Description to the notification"

            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager?.createNotificationChannel(notificationChannel)

        }
    }
}