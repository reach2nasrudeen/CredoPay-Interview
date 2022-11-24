package com.interview.app.base

import android.app.Application
import com.interview.app.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class InterviewApp : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@InterviewApp)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    apiModule,
                    networkModule,
                    databaseModule,
                    viewModelModule
                )
            )
        }
    }
}