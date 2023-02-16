package com.example.harvardartmuseumsproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.harvardartmuseumsproject.model.Gallery

@Database(entities = [Gallery::class], version = 1, exportSchema = false)
abstract class GalleryDatabase : RoomDatabase() {
    abstract fun galleryDao(): GalleryDao

    companion object {
        @Volatile
        private var instance: GalleryDatabase? = null

        fun getInstance(context: Context): GalleryDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): GalleryDatabase {
            return Room.databaseBuilder(context, GalleryDatabase::class.java, "gallery_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
