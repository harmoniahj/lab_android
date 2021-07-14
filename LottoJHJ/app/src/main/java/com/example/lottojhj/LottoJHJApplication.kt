package com.example.lottojhj

import android.app.Application
import android.content.Context

class LottoJHJApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this;
    }

    override fun onTerminate() {
        super.onTerminate()
        appContext = null
    }

    companion object { // = static
        var appContext: Context? = null
        private set
    }
}