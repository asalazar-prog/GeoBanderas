package com.asalazar.geobanderas

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import com.asalazar.geobanderas.di.appModule
import com.asalazar.geobanderas.di.networkModule
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class GeoBanderasApp : Application(), SingletonImageLoader.Factory {

    private val imageLoader: ImageLoader by inject()

    override fun onCreate() {
        super.onCreate()

        val modules = listOf(networkModule, appModule)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@GeoBanderasApp)
            modules(modules)
        }
    }

    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return imageLoader
    }
}
