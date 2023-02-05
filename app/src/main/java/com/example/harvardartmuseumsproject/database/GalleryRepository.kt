package com.example.harvardartmuseumsproject.database

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.harvardartmuseumsproject.model.Gallery

class GalleryRepository(private val galleryDao: GalleryDao) {

    fun getPagedGalleries(): LiveData<PagedList<Gallery>> {
        val dataSourceFactory = galleryDao.getDataSourceFactory()
        return LivePagedListBuilder(dataSourceFactory, 20).build()
    }
}
