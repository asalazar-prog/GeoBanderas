package com.asalazar.geobanderas.data

import com.asalazar.geobanderas.domain.Country
import retrofit2.http.GET
import retrofit2.http.Query

interface CountryService {

    @GET("region/americas")
    suspend fun getAmericaCountries(
        @Query("fields") fields: List<String> = listOf("name,flags,capital,latlng")
    ): List<Country>

}
