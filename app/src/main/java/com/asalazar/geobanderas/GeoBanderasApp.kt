package com.asalazar.geobanderas

import android.app.Application
import com.asalazar.geobanderas.di.appModule
import com.asalazar.geobanderas.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class GeoBanderasApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@GeoBanderasApp)
            modules(listOf(networkModule, appModule))
        }
    }
}
