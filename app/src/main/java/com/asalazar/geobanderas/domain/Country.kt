package com.asalazar.geobanderas.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Country(
    @SerialName("name")
    val name: CountryName,
    @SerialName("flags")
    val flags: CountryFlag,
    @SerialName("latlng")
    val latlng: List<Double>,
    @SerialName("capital")
    val capital: List<String> = emptyList()
)
