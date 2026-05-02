package com.asalazar.geobanderas.di

import coil3.ImageLoader
import coil3.network.okhttp.OkHttpNetworkFetcherFactory
import com.asalazar.geobanderas.data.CountryService
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

val networkModule = module {
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
            isLenient = true
        }
    }

    single {
        OkHttpClient
            .Builder()
            .cache(Cache(androidContext().cacheDir, 50 * 1024 * 1024))
            .build()
    }

    single {
        val json: Json = get()
        val okHttpClient: OkHttpClient = get()

        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    single<CountryService> {
        val retrofit: Retrofit = get()
        retrofit.create(CountryService::class.java)
    }

    single {
        ImageLoader.Builder(androidContext())
            .components {
                add(OkHttpNetworkFetcherFactory(get<OkHttpClient>()))
            }
            .build()
    }
}
