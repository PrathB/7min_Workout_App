package com.test.a7minworkout

import android.app.Application
//this file will create and return the instance of history database
class HistoryApp: Application() {
    val db by lazy{
        HistoryDatabase.getInstance(this)
    }
}