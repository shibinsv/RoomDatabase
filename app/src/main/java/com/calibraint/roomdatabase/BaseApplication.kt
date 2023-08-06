package com.calibraint.roomdatabase

import android.app.Application
import com.calibraint.roomdatabase.room.AppDatabase

class BaseApplication : Application() {

     lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        database = AppDatabase.getDatabase(this)
    }
}