package com.example.harvardartmuseumsproject.viewmodel

import androidx.lifecycle.ViewModel
import com.example.harvardartmuseumsproject.data.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryListDetailsViewModel @Inject constructor(
    private val repository: ArtRepository
) : ViewModel() {

}
