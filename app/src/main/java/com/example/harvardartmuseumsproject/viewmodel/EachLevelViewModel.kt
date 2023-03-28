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

    private val _screenState = MutableLiveData<ScreenState<Galleries>>()
    val screenState: LiveData<ScreenState<Galleries>> = _screenState

    init {
        getGallery(level)
    }

    private fun getGallery(level: Int) = viewModelScope.launch {
        _screenState.postValue(ScreenState.Loading)
        try {
            withContext(Dispatchers.IO) {
                val listOfGalleries = KtorService.create().getGalleries(level = level)
                _screenState.postValue(ScreenState.Success(listOfGalleries as Galleries))
            }
        } catch (e: Exception) {
            _screenState.postValue(ScreenState.Error(e.message ?: "Unknown error occurred"))
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
