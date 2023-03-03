package com.example.harvardartmuseumsproject

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.harvardartmuseumsproject.viewmodel.FullSizeImageViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harvardartmuseumsproject.model.ArtObject
import com.example.harvardartmuseumsproject.model.FullSizeImage
import com.example.harvardartmuseumsproject.viewmodel.GalleryListDetailsViewModel


@Composable
fun FullSizeImageScreen(
    imageId: String,
    navController: NavController,
    viewModel: FullSizeImageViewModel = viewModel(
        factory = FullSizeImageViewModel.factory(imageId)
    )
) {
    val viewState = viewModel.liveData.observeAsState()

    if (viewState.value == null) {
        ProgressIndicator()
    } else {
        viewState.value?.let {
           ImageScreen(
                fullSizeImage = it,
               secondImage = it,
                navController = navController
            )
        } ?: ErrorHandlingMessage()
    }
}

@Composable
fun ImageScreen(
    fullSizeImage: FullSizeImage,
    secondImage: FullSizeImage,
    navController: NavController
) {
    Column(
        Modifier
            .padding(4.dp)
            .fillMaxSize()
    ) {
        val imageUrl = fullSizeImage.primaryImageUrl
        if (imageUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                // placeholder = painterResource(R.mipmap.ic_launcher),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(300.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
        } else {
            Text(
                text = "No image available)",
                modifier = Modifier
                    //    .align(Alignment.CenterStart)
                    .padding(8.dp)
                    .size(300.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
        }
        val image12 = fullSizeImage.primaryImageUrl
        if (image12 != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true)
                    .build(),
                // placeholder = painterResource(R.mipmap.ic_launcher),
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .size(300.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
        }
    }
}

//    @Composable
//    fun ImageList(
//        navController: NavController,
//        listOfObjects: List<FullSizeImage>
//    ) {
//        LazyColumn {
//            itemsIndexed(items = listOfObjects) { index, item ->
//                ImageScreen(
//                    navController = navController,
//                    fullSizeImage = item
//                )
//            }
//        }
//    }
