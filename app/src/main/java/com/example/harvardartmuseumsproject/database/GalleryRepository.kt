package com.example.harvardartmuseumsproject.database

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.harvardartmuseumsproject.model.Gallery

class GalleryRepository(private val galleryDao: GalleryDao) {

    fun getPagedGalleries(): LiveData<PagingData<Gallery>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { GalleryDataSource(galleryDao) }
        ).liveData
    }
}
