package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.ScreenState
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.Galleries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EachLevelViewModel(
    level: Int
) : ViewModel() {

    private var _liveData = MutableLiveData<ScreenState<Galleries>>()
    val liveData: LiveData<ScreenState<Galleries>> = _liveData

    init {
        getGallery(level)
    }

    private fun getGallery(level: Int) {
        _liveData.value = ScreenState.Loading
        viewModelScope.launch {
        try {
            val galleries = KtorService.create().getGalleries(level = level)
            if (galleries == null) {
                _liveData.value = ScreenState.Error("No galleries found")
            } else {
                _liveData.value = ScreenState.Success(galleries)
            }
        } catch (e: Exception) {
            _liveData.value = ScreenState.Error(e.message ?: "Unknown error occurred")
        }
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
