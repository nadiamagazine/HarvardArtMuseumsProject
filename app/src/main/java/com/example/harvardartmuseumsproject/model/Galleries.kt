package com.example.harvardartmuseumsproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Galleries(
    val records: List<Gallery>
)

@Serializable
data class Gallery(
    val name: String?,
)

@Serializable
data class Groups(
    val records: List<Group>
)
@Serializable
data class Group(
    val id: Int,
    val name: String?,
   val  baseImageUrl: String?,
    val description: String?

)
