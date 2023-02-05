package com.example.harvardartmuseumsproject.database

import androidx.paging.PageKeyedDataSource
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.Gallery

abstract class GalleryDataSource(
    private val galleryDao: GalleryDao,
    private val ktorService: KtorService
) : PageKeyedDataSource<Int, Gallery>() {

    companion object {
        const val PAGE_SIZE = 40
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Gallery>
    ) {
        val galleries =
            galleryDao.getGalleriesByLevelWithPaging(level = 0, limit = PAGE_SIZE, offset = 0)
        callback.onResult(galleries, null, 1)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Gallery>) {
        val galleries = galleryDao.getGalleriesByLevelWithPaging(
            level = 0,
            limit = PAGE_SIZE,
            offset = params.key * PAGE_SIZE
        )
        callback.onResult(galleries, params.key + 1)
    }
}
