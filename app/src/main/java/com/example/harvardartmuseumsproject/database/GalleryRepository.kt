package com.example.harvardartmuseumsproject.database

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.example.harvardartmuseumsproject.model.Gallery

class GalleryRepository(private val galleryDao: GalleryDao) {

    fun getPagedGalleries(level: Int): LiveData<PagingData<Gallery>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { GalleryDataSource(galleryDao, level) }
        ).liveData
    }
}

class GalleryDataSource(
    private val galleryDao: GalleryDao,
    private val level: Int
) : PagingSource<Int, Gallery>() {

    companion object {
        const val PAGE_SIZE = 20
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gallery> {
        try {
            val offset = (params.key ?: 0) * PAGE_SIZE
            val galleries = galleryDao.getGalleriesByLevelWithPaging(
                level = level,
                limit = PAGE_SIZE,
                offset = offset
            )
            return LoadResult.Page(
                data = galleries,
                prevKey = if (offset == 0) null else params.key?.minus(1),
                nextKey = if (galleries.isEmpty()) null else params.key?.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Gallery>): Int? {
        TODO("Not yet implemented")
    }
}
