package com.example.harvardartmuseumsproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harvardartmuseumsproject.model.Gallery
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.harvardartmuseumsproject.viewmodel.GalleryOfEachLevelViewModel

@Composable
fun LevelTwoDetailsScreen(
    navController: NavController,
    viewModel: GalleryOfEachLevelViewModel = viewModel()
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

@Composable
fun GalleryRow(
    navController: NavController,
    gallery: Gallery
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colors.surface)
            .clickable {
                TODO()
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Row(
            Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
            gallery.records.name.let {
                if (it != null) {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body1,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun GalleryList(
    navController: NavController,
    listOfGalleries: List<Gallery>
) {
    LazyColumn {
        itemsIndexed(items = listOfGalleries) { index, item ->
            GalleryRow(
                navController = navController,
                gallery = item
            )
        }
    }
}

@Composable
fun ProgressIndicator() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.Center),
            color = Color.Green,
            strokeWidth = 10.dp
        )
    }
}

@Composable
fun ErrorHandlingMessage() {
    Text(
        text = "Oooops, something went wrong",
        fontSize = 30.sp,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    )
}


