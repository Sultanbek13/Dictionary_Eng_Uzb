package com.sultandev.dictionaryeng_uzb.app

import android.app.Application
import com.sultandev.dictionaryeng_uzb.BuildConfig
import com.sultandev.dictionaryeng_uzb.di.dataModule
import com.sultandev.dictionaryeng_uzb.di.domainModule
import com.sultandev.dictionaryeng_uzb.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@App)
            androidFileProperties()
            modules(listOf(dataModule, domainModule, viewModelModule))
        }
    }
}