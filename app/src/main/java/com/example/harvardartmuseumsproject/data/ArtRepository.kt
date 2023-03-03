package com.example.harvardartmuseumsproject.data

import com.example.harvardartmuseumsproject.model.ArtObjects
import com.example.harvardartmuseumsproject.model.FullSizeImage
import com.example.harvardartmuseumsproject.model.Galleries
import com.example.harvardartmuseumsproject.model.Gallery

interface ArtRepository {
    suspend fun getGalleries(level: Int): Galleries?
    suspend fun getGallery(id: String): Gallery?
    suspend fun getObjects(galleryId: String): ArtObjects?
    suspend fun getImages(imageId: String): FullSizeImage?
}
