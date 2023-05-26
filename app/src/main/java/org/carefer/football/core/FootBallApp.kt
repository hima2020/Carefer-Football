package org.carefer.football.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FootBallApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}