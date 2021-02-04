@file:Suppress("KDocUnresolvedReference")
package com.himanshu.backgroundtasks

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.work.*
import com.himanshu.backgroundtasks.databinding.ActivityMainBinding
import com.himanshu.backgroundtasks.execution.workmanager.NotifWorker
import com.himanshu.backgroundtasks.viewmodel.MainViewModel
import java.util.concurrent.TimeUnit

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
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val uploadWorkRequest: WorkRequest =
            OneTimeWorkRequestBuilder<NotifWorker>()
                .setConstraints(constraints)
                .setInitialDelay(60, TimeUnit.SECONDS)
                .build()

        WorkManager
            .getInstance(this)
            .enqueue(uploadWorkRequest)
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