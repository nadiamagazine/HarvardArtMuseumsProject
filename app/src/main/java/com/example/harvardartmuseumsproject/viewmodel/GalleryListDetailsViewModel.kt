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
    name: String
) : ViewModel() {

    private var _liveData = MutableLiveData<Groups>()
    val liveData: LiveData<Groups> = _liveData

    init {
        getListOfGroupsOfEachGallery(name)
    }

    private fun getListOfGroupsOfEachGallery(name: String) = viewModelScope.launch {
        val galleryGroup = withContext(Dispatchers.IO) {
            KtorService.create().getListOfGroupsOfEachGallery(name = name)
        }
        _liveData.postValue(galleryGroup)
    }

    companion object {
        fun factory(name: String): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    GalleryListDetailsViewModel(name)
                }
            }
    }
}
