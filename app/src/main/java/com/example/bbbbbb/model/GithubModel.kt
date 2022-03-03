package com.example.bbbbbb.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Metrics(
    @SerialName("health_percentage")
    val healthPercentage: Int?,
    val description: String?,
    val documentation: String?
)

@Serializable
data class Organisation(
    val login: String,
    val id: Int,
    val description: String?
)