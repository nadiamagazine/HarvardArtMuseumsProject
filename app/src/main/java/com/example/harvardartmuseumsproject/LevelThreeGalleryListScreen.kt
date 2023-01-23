package com.example.harvardartmuseumsproject

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.harvardartmuseumsproject.viewmodel.LevelThreeViewModel


@Composable
fun LevelThreeGalleryListScreen(
    navController: NavController,
    viewModel: LevelThreeViewModel = viewModel()
) {
    val viewState = viewModel.liveData.observeAsState()

    if (viewState.value == null) {
        ProgressIndicator()
    } else {
        Column() {
            viewState.value?.let {
                if (it.isNotEmpty()) {
                    GalleryList(
                        navController = navController,
                        listOfGalleries = it
                    )
                } else {
                    ErrorHandlingMessage()
                }

            }
        }
    }
}
