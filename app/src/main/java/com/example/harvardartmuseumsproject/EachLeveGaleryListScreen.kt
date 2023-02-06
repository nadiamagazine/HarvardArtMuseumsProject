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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.harvardartmuseumsproject.model.Galleries
import com.example.harvardartmuseumsproject.model.Gallery
import com.example.harvardartmuseumsproject.viewmodel.EachLevelViewModel

@Composable
fun EachLevelGalleryListScreen(
    level: Int,
    navController: NavController,
    viewModel: EachLevelViewModel = viewModel(
        factory = EachLevelViewModel.factory(level)
    )
) {
    val viewState = viewModel.liveData.observeAsState()

    if (viewState.value == null) {
        ProgressIndicator()
    } else {
        Column() {
            viewState.value?.let {
                GalleryList(
                    navController = navController,
                    listOfGalleries = it
                )

            } ?:  ErrorHandlingMessage()
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
                navController.navigate("GalleryListDetailsScreen/${gallery.name}")
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
    ) {
        Column(
            Modifier
                .padding(4.dp)
                .fillMaxSize()
        ) {
                Text(
                    text = gallery.name ?: "",
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

@Composable
fun GalleryList(
    navController: NavController,
    listOfGalleries: Galleries
) {
    LazyColumn {
        itemsIndexed(items = listOfGalleries.records) { index, item ->
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
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = "Oooops, something went wrong",
            fontSize = 30.sp
        )
    }
}
