package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.model.Gallery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LevelFiveViewModel : ViewModel() {

    private var _liveData = MutableLiveData<List<Gallery>>()
    val liveData: LiveData<List<Gallery>> = _liveData

    init {
        getGallery()
    }

    private fun getGallery() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val listOfGalleries = KtorService.create().getListOfGalleriesOnEachLevel(5)
            _liveData.postValue(listOfGalleries)
        }
    }
}
