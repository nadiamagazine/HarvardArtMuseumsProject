package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.harvardartmuseumsproject.api.KtorService
import com.example.harvardartmuseumsproject.api.KtorServiceImplementation
import com.example.harvardartmuseumsproject.data.ArtRepository
import com.example.harvardartmuseumsproject.data.ArtRepositoryImp
import com.example.harvardartmuseumsproject.model.Galleries
import io.ktor.client.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EachLevelViewModel(
    level: Int,
    repository: ArtRepository
) : ViewModel() {

    private var _liveData = MutableLiveData<Galleries>()
    val liveData: LiveData<Galleries> = _liveData

    init {
        viewModelScope.launch {
            val listOfGalleries = repository.getGalleries(level = level)
            _liveData.postValue(listOfGalleries)
        }
    }

    companion object {
        fun factory(level: Int): ViewModelProvider.Factory =
            viewModelFactory {
                val repository =
                    ArtRepositoryImp.ArtRepositoryImpl(KtorServiceImplementation(HttpClient()))
                EachLevelViewModel(level, repository)
            }
    }
}

