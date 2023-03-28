package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.ScreenState
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.FullSizeImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FullSizeImageViewModel(
    imageId: String
) : ViewModel() {

    private val _screenState = MutableLiveData<ScreenState<FullSizeImage>>()
    val screenState: LiveData<ScreenState<FullSizeImage>> = _screenState

    init {
        getImage(imageId)
    }

    private fun getImage(imageId: String) = viewModelScope.launch {
        _screenState.value = ScreenState.Loading
        try {
            val fullSizeImage = withContext(Dispatchers.IO) {
                KtorService.create().getImages(imageId = imageId)
            }
            _screenState.value = ScreenState.Success(fullSizeImage as FullSizeImage)
        } catch (e: Exception) {
            _screenState.value = ScreenState.Error("Failed to load image: ${e.message}")
        }
    }

    companion object {
        fun factory(imageId: String): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    FullSizeImageViewModel(imageId)
                }
            }
    }
}
