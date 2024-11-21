package com.example.cvscodingchallenge

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlickrSearchApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Any app-wide initialization can go here
    }
}