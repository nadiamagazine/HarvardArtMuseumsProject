package com.example.harvardartmuseumsproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.harvardartmuseumsproject.model.ArtObject
import com.example.harvardartmuseumsproject.viewmodel.GalleryListDetailsViewModel

@Composable
fun GalleryListDetailsScreen(
    id: String,
    onNavigateToFullSizeImageScreen: (String) -> Unit = {},
    viewModel: GalleryListDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = GalleryListDetailsViewModel.factory(id)
    )
) {
    val viewState = viewModel.liveData.observeAsState()

    if (viewState.value == null) {
        ProgressIndicator()
    } else {
        viewState.value?.let {
            GroupList(
               onImageClick = { artObject ->
                   artObject.images.firstOrNull()?.imageid.let { imageId ->
                       if (imageId != null) {
                           onNavigateToFullSizeImageScreen(imageId)
                       }

                   }
               },
                listOfObjects = it.records
            )
        } ?: ErrorHandlingMessage()
    }
}


@Composable
fun GroupRow(
    artObject: ArtObject,
    onImageClick: (ArtObject) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(bottom = 16.dp)

    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
                .clickable {
                    onImageClick(artObject)
                },
            shape = RoundedCornerShape(8.dp),
            elevation = 4.dp
        ) {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                val imageUrl = artObject.primaryImageUrl
                if (imageUrl != null) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(imageUrl)
                            .crossfade(true)
                            .build(),
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
                            .padding(8.dp)
                            .size(300.dp)
                            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxSize()
                        .align(Alignment.CenterVertically)
                ) {
                    artObject.objectNumber.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    artObject.imageId?.let {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.body1,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    artObject.description?.let {
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
}

@Composable
fun GroupList(
    onImageClick: (ArtObject) -> Unit = {},
    listOfObjects: List<ArtObject>
) {
    LazyColumn {
        itemsIndexed(items = listOfObjects) { _, image ->
            GroupRow(
                artObject = image,
                onImageClick = { onImageClick(image) }
            )
        }
    }
}
