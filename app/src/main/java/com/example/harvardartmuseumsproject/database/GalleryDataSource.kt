package com.example.harvardartmuseumsproject.database

import androidx.paging.PagingSource
import com.example.harvardartmuseumsproject.model.Gallery

abstract class GalleryDataSource(
    private val galleryDao: GalleryDao
) : PagingSource<Int, Gallery>() {

    companion object {
        const val PAGE_SIZE = 40
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gallery> {
        val offset = (params.key?.minus(1))?.times(PAGE_SIZE)
        val galleries = offset?.let {
            galleryDao.getGalleriesByLevelWithPaging(
                level = 0,
                limit = PAGE_SIZE,
                offset = it
            )
        }
        val nextKey = if (galleries?.isNotEmpty() == true) params.key?.plus(1) else null

        return LoadResult.Page(
            data = galleries,
            prevKey = null,
            nextKey = nextKey
        )
    }
}



