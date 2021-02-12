package com.himanshu.backgroundtasks.execution.async

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.text.format.DateUtils
import androidx.core.app.AlarmManagerCompat
import com.himanshu.backgroundtasks.App
import com.himanshu.backgroundtasks.db.models.PostModel
import com.himanshu.backgroundtasks.execution.receiver.NotificationExecutor
import com.himanshu.backgroundtasks.network.NetworkRepository
import com.himanshu.backgroundtasks.utils.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CustomThread(private val p0: Context): Runnable {

    override fun run() {
        GlobalScope.launch {
            saveNetworkData()
        }
    }

    private suspend fun saveNetworkData() {

        val items = App.db.postDao().getAllItems()
        if(items.size > 10) {
            val notifyIntent = Intent(p0, NotificationExecutor::class.java)
            val alarmManager = p0.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val notifyPendingIntent = PendingIntent.getBroadcast(
                p0,
                0,
                notifyIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            alarmManager.cancel(notifyPendingIntent)
        }

        val time = System.currentTimeMillis()
        App.db.postDao().insertItem(
            PostModel(userId = time, id = time, title = time.toString(), body = time.toString())
        )
    }

}