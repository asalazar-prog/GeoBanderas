package com.asalazar.geobanderas.domain

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryFlag(
    @SerialName("png")
    val pngUrl: String,
    @SerialName("alt")
    val contentDescription: String? = null
)
