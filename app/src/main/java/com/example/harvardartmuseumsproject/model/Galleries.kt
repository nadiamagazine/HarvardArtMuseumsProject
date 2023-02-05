package com.example.harvardartmuseumsproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
data class Galleries(
    val records: List<Gallery>
)

@Entity
@Serializable
data class Gallery(
    @PrimaryKey  val id: Int,
    val name: String?,
    val floor: Int
)
