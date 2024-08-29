package com.pavelshelkovenko.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OfferDto(
    @SerialName("id") val id: String? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("link") val link: String? = null,
    @SerialName("button") val button: ButtonDto? = null,
)

@Serializable
class ButtonDto(
    @SerialName("text")
    val text: String? = null
)