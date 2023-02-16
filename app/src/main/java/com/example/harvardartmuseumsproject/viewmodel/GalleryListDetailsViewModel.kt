package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.Gallery
import com.example.harvardartmuseumsproject.model.Groups
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GalleryListDetailsViewModel(
    id: String
) : ViewModel() {

    private var _liveData = MutableLiveData<Gallery>()
    val liveData: LiveData<Gallery> = _liveData

    init {
        getListOfGroupsOfEachGallery(id)
    }

    private fun getListOfGroupsOfEachGallery(id: String) = viewModelScope.launch {
        val galleryGroup = withContext(Dispatchers.IO) {
            KtorService.create().getListOfGroupsOfEachGallery(id = id)
        }
        _liveData.postValue(galleryGroup)
    }

    companion object {
        fun factory(id: String): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    GalleryListDetailsViewModel(id)
                }
            }
    }
}
