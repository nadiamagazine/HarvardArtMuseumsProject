package com.example.harvardartmuseumsproject.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Galleries(
    @SerialName("records") val records: List<GalleryRecord>
)

@Serializable
data class Gallery(
    @SerialName("galleryid") val galleryId: String?,
    @SerialName("name") val name: String?,
    @SerialName("contains") val contains: Groups
)

@Serializable
data class GalleryRecord(
    @SerialName("galleryid") val galleryId: String?,
    @SerialName("name") val name: String?,
    )

@Serializable
data class Groups(
    @SerialName("groups") val groups: List<GalleryGroup>
)

@Serializable
data class GalleryGroup(
    @SerialName("groupid") val groupId: String?,
    @SerialName("name") val name: String?
)

@Serializable
data class ArtObjects(
    @SerialName("records") val records: List<ArtObject>
)

@Serializable
data class ArtObject(
    @SerialName("primaryimageurl") val primaryImageUrl: String?,
    @SerialName("objectnumber") val objectNumber: String,
    @SerialName("description") val description: String?,
)

@Serializable
data class FullSizeImage(
    @SerialName("primaryimageurl") val primaryImageUrl: String?,
)
