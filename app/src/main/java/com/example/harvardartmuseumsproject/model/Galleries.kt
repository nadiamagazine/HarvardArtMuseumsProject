package com.example.harvardartmuseumsproject.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Galleries(
    @SerialName("records") val records: List<Gallery>
)

@Serializable
data class Gallery(
    @SerialName("galleryid") val galleryId: String?,
    @SerialName("name") val name: String?,
    @SerialName("contains") val contains: Groups
)

@Serializable
data class Groups(
    @SerialName("groups") val groups: List<Group>
)

@Serializable
data class Group(
    @SerialName("groupid") val groupId: String?,
    @SerialName("name") val name: String?
)
