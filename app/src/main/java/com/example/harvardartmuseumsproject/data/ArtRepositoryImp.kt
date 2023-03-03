package com.example.harvardartmuseumsproject.data

import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.ArtObjects
import com.example.harvardartmuseumsproject.model.FullSizeImage
import com.example.harvardartmuseumsproject.model.Galleries
import com.example.harvardartmuseumsproject.model.Gallery

class ArtRepositoryImp {
    class ArtRepositoryImpl(private val service: KtorService) : ArtRepository {

        override suspend fun getGalleries(level: Int): Galleries? = service.getGalleries(level)

        override suspend fun getGallery(id: String): Gallery? = service.getGallery(id)

        override suspend fun getObjects(galleryId: String): ArtObjects? = service.getObjects(galleryId)

        override suspend fun getImages(imageId: String): FullSizeImage? = service.getImages(imageId)
    }
}
