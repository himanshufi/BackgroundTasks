package com.himanshu.backgroundtasks.execution.async

import com.himanshu.backgroundtasks.App
import com.himanshu.backgroundtasks.db.models.PostModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CustomThread: Runnable {

    override fun run() {
        GlobalScope.launch {
            saveNetworkData()
        }
    }

    private suspend fun saveNetworkData() {
        val time = System.currentTimeMillis()
        App.db.postDao().insertItem(
            PostModel(userId = time, id = time, title = time.toString(), body = time.toString())
        )
    }

}