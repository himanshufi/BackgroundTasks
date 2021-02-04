package com.himanshu.backgroundtasks

import android.app.Application
import com.himanshu.backgroundtasks.db.DbRepository

class App: Application() {

    companion object {
        lateinit var db: DbRepository
    }

    override fun onCreate() {
        super.onCreate()
        db = DbRepository.getInstance(applicationContext)
    }
}