package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.ScreenState
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.ArtObjects
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class GalleryListDetailsViewModel(
    id: String
) : ViewModel() {

    private var _screenState = MutableLiveData<ScreenState<ArtObjects>>(ScreenState.Loading)
    val screenState: LiveData<ScreenState<ArtObjects>> = _screenState

    init {
        getObjects(id)
    }

    private fun getObjects(id: String) = viewModelScope.launch {
        _screenState.postValue(ScreenState.Loading)
        try {
            val galleryObjects = withContext(Dispatchers.IO) {
                KtorService.create().getObjects(galleryId = id)
            }
            _screenState.postValue(ScreenState.Success(galleryObjects as ArtObjects))
        } catch (e: Exception) {
            _screenState.postValue(ScreenState.Error(e.message ?: "Unknown error occurred."))
        }
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
