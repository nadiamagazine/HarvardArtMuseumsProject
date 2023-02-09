package com.example.harvardartmuseumsproject.database

//import androidx.paging.PagingSource
//import com.example.harvardartmuseumsproject.model.Gallery
//
//abstract class GalleryDataSource(
//    private val galleryDao: GalleryDao
//) : PagingSource<Int, Gallery>() {
//
//    companion object {
//        const val PAGE_SIZE = 40
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gallery> {
//        try {
//            val page = params.key ?: 0
//            val offset = page * PAGE_SIZE
//            val galleries = galleryDao.getGalleriesByLevelWithPaging(
//                level = 0,
//                limit = PAGE_SIZE,
//                offset = offset
//            )
//
//            return LoadResult.Page(
//                data = galleries,
//                prevKey = if (page == 0) null else page - 1,
//                nextKey = if (galleries.isEmpty()) null else page + 1
//            )
//
//        } catch (e: Exception) {
//            return LoadResult.Error(e)
//        }
//    }
//}



