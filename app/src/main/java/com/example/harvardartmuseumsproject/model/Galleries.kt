package com.example.harvardartmuseumsproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Galleries(
    val records: List<Gallery>
)

@Serializable
data class Gallery(
    val name: String?,
    val floor: Int
)
