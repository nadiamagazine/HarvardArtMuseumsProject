package com.example.harvardartmuseumsproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Gallery(
    val records: List<Records>?
)

@Serializable
data class Records(
    val name: String?
)
