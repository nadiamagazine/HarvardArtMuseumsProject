package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.Groups
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GalleryListDetailsViewModel(
    galleryGroupName: String
) : ViewModel() {

    private var _liveData = MutableLiveData<Groups>()
    val liveData: LiveData<Groups> = _liveData

    init {
        getListOfGroupsOfEachGallery(galleryGroupName)
    }

    private fun getListOfGroupsOfEachGallery(galleryGroupName: String) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val galleryGroup = KtorService.create()
                .getListOfGroupsOfEachGallery(galleryGroupName = galleryGroupName)
            _liveData.postValue(galleryGroup)
        }
    }

    companion object {
        fun factory(galleryGroupName: String): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    GalleryListDetailsViewModel(galleryGroupName)
                }
            }
    }
}
