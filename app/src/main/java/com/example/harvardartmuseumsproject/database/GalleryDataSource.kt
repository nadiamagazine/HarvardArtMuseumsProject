package com.example.harvardartmuseumsproject.database

import androidx.paging.PagingSource
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.Gallery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class GalleryDataSource(
    private val KtorService: KtorService,
    private val level: Int,
    private val totalrecords: Int,
    private val totalrecordsperquery: Int,
) : PagingSource<Int, Gallery>() {

    companion object {
        const val PAGE_START = 10
        const val PAGE_SIZE = 40
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Gallery> {
        val page = params.key ?: PAGE_START
       return try {
           withContext(Dispatchers.IO) {
               val response = KtorService.getListOfGalleriesOnEachLevel(
                   level,
                   totalrecords,
                   totalrecordsperquery
               )
               val pages = response?.records
               val nextKey = if (pages?.isEmpty() == true) {
                   null
               } else {
                   totalrecords + PAGE_SIZE
               }
               LoadResult.Page(
                   data = pages,
                   prevKey = if (page == PAGE_START) {
                       null
                   } else {
                       page - PAGE_SIZE
                   },
                   nextKey = nextKey
               )
           }

        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}



