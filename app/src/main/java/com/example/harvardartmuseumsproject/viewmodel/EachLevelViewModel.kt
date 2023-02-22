package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.Galleries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EachLevelViewModel(
    level: Int
) : ViewModel() {

    private var _liveData = MutableLiveData<Galleries>()
    val liveData: LiveData<Galleries> = _liveData

    init {
        getGallery(level)
    }

    private fun getGallery(level: Int) = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val listOfGalleries = KtorService.create().getGalleries(level = level)
            _liveData.postValue(listOfGalleries)
        }
    }

    companion object {
        fun factory(level: Int): ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    EachLevelViewModel(level)
                }
            }
    }
}
