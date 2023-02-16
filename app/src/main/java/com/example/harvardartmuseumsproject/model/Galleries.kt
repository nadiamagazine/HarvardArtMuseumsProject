package com.example.harvardartmuseumsproject.model

import kotlinx.serialization.Serializable

@Serializable
data class Galleries(
    val records: List<Gallery>
)

@Serializable
data class Gallery(
    val galleryid: String?,
    val name: String?,
    val contains: Groups
)

@Serializable
data class Groups(
    val groups: List<Group>
)

@Serializable
data class Group(
    val groupid: String?,
    val name: String?
)
