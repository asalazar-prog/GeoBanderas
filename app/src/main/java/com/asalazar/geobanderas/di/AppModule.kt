package com.asalazar.geobanderas.di

import com.asalazar.geobanderas.data.CountryRepository
import com.asalazar.geobanderas.presentation.ui.countries.CountriesViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::CountryRepository)
    viewModelOf(::CountriesViewModel)
}
