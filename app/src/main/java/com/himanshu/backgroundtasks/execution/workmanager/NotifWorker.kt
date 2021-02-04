package com.himanshu.backgroundtasks.execution.workmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import android.text.format.DateUtils
import androidx.core.app.AlarmManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.himanshu.backgroundtasks.App
import com.himanshu.backgroundtasks.db.models.PostModel
import com.himanshu.backgroundtasks.execution.receiver.NotificationExecutor
import com.himanshu.backgroundtasks.network.NetworkRepository
import com.himanshu.backgroundtasks.utils.Constants

class NotifWorker(appContext: Context, workerParams: WorkerParameters
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {

        /**
         * When a currently running Worker is stopped for any reason, it receives a call to
         * Worker.onStopped(). Override this method or call Worker.isStopped() to checkpoint
         * your code and free up resources when necessary. When the Worker in the example above
         * is stopped, it may be in the middle of its loop of downloading items and will continue
         * doing so even though it has been stopped.
         *
         * In case of a loop, use this in it: if(isStopped) break
         */

        val data = executeNetworkRequest()
        val isDataSaved = saveNetworkData(data)

        val notifyIntent = Intent(applicationContext, NotificationExecutor::class.java)
        notifyIntent.putExtra(Constants.STATUS, isDataSaved)
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
            SystemClock.elapsedRealtime() + DateUtils.SECOND_IN_MILLIS,
            notifyPendingIntent
        )

        return Result.success()
    }

    private suspend fun executeNetworkRequest(): List<PostModel>? {
        val request = NetworkRepository.getAllPosts()
        return if(request.isSuccessful) request.body() else null
    }

    private suspend fun saveNetworkData(data: List<PostModel>?):Boolean {
        return if(!data.isNullOrEmpty()) {
            App.db.postDao().deleteAll()
            val response = App.db.postDao().insertItems(data)
            response.isNotEmpty()
        }else false
    }

}