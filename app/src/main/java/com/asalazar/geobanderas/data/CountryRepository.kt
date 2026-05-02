package com.asalazar.geobanderas.data

import com.asalazar.geobanderas.domain.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepository(
    private val countryService: CountryService
) {

    suspend fun getAmericaCountries(): List<Country> = withContext(Dispatchers.IO) {
        countryService.getAmericaCountries()
    }

}
