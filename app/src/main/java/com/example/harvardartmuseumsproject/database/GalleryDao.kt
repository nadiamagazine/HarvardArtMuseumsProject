package com.example.harvardartmuseumsproject.database

import androidx.room.Dao
import androidx.room.Query
import com.example.harvardartmuseumsproject.model.Gallery

@Dao
interface GalleryDao {
    @Query("SELECT * FROM galleries WHERE level = :level LIMIT :limit OFFSET :offset")
    fun getGalleriesByLevelWithPaging(level: Int, limit: Int, offset: Int): List<Gallery>
}