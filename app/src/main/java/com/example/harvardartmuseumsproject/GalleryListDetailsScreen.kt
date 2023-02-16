package com.example.harvardartmuseumsproject

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.harvardartmuseumsproject.model.Group
import com.example.harvardartmuseumsproject.model.Groups
import com.example.harvardartmuseumsproject.viewmodel.GalleryListDetailsViewModel

@Composable
fun GalleryListDetailsScreen(
    id: String,
    navController: NavController,
    viewModel: GalleryListDetailsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = GalleryListDetailsViewModel.factory(id)
    )
) {
    val viewState = viewModel.liveData.observeAsState()

    if (viewState.value == null) {
        ProgressIndicator()
    } else {
        Column() {
            viewState.value?.let {
                GroupList(
                    navController = navController,
                    listOfGroups = it.contains.groups
                )
            } ?: ErrorHandlingMessage()
        }
    }
}


@Composable
fun GroupRow(
    group: Group,
    navController: NavController
) {
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .background(Color.White)
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(bottom = 16.dp)

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
            Column(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                group.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h3,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun GroupList(
    navController: NavController,
    listOfGroups: List<Group>
) {
    LazyColumn {
        itemsIndexed(items = listOfGroups) { index, item ->
            GroupRow(
                navController = navController,
                group = item
            )
        }
    }
}
