package com.asalazar.geobanderas.di

import com.asalazar.geobanderas.data.CountryService
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
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
            .build()
    }

    single {
        val json: Json = get()
        val okHttpClient: OkHttpClient = get()

        Retrofit.Builder()
            .baseUrl("https://restcountries.com/v3.1/")
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .build()
    }

    single<CountryService> {
        val retrofit: Retrofit = get()
        retrofit.create(CountryService::class.java)
    }

}
