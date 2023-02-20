package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.Gallery
import com.example.harvardartmuseumsproject.model.Objects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GalleryListDetailsViewModel(
    id: String
) : ViewModel() {

    private var _liveData = MutableLiveData<Objects>()
    val liveData: LiveData<Objects> = _liveData

    init {
        getObjects(id)
    }

    private fun getObjects(id: String) = viewModelScope.launch {
        val galleryObjects = withContext(Dispatchers.IO) {
            KtorService.create().getObjects(galleryId = id)
        }
        _liveData.postValue(galleryObjects)
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
