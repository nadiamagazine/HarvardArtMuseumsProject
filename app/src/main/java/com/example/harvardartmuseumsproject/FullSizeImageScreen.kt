package com.example.harvardartmuseumsproject

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harvardartmuseumsproject.model.FullSizeImage
import com.example.harvardartmuseumsproject.viewmodel.FullSizeImageViewModel


@Composable
fun FullSizeImageScreen(
    imageId: String,
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
                fullSizeImage = it
            )
        } ?: ErrorHandlingMessage()
    }
}

@Composable
fun ImageScreen(
    fullSizeImage: FullSizeImage
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
                contentDescription = null,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
        } else {
            Text(
                text = "No image available)",
                modifier = Modifier
                    .padding(8.dp)
                    .size(300.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            )
        }
    }
}
