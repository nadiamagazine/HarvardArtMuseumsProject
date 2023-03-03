package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.FullSizeImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FullSizeImageViewModel(
    imageId: String
) : ViewModel() {

    private var _liveData = MutableLiveData<FullSizeImage>()
    val liveData: LiveData<FullSizeImage> = _liveData

    init {
        getImage(imageId)
    }

    private fun getImage(imageId: String) = viewModelScope.launch {
        val galleryImage = withContext(Dispatchers.IO) {
            KtorService.create().getImages(imageId = imageId)
        }
        _liveData.postValue(galleryImage)
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
