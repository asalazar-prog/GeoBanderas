package com.asalazar.geobanderas.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryName(
    @SerialName("official")
    val official: String
)
