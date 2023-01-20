package com.example.harvardartmuseumsproject.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    val records: Records
)

@Serializable
data class Records(
    val name: String?
)
