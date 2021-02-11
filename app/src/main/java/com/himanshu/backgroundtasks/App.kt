package com.himanshu.backgroundtasks

import android.app.Application
import android.os.Handler
import android.os.Looper
import com.himanshu.backgroundtasks.db.DbRepository

class App: Application() {

    companion object {
        lateinit var handler: Handler
        lateinit var db: DbRepository
    }

    override fun onCreate() {
        super.onCreate()
        handler = Handler(Looper.getMainLooper())
        db = DbRepository.getInstance(applicationContext)
    }
}