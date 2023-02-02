package com.example.harvardartmuseumsproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ExhibitionScreen(
    navController: NavController
) {
    val levels = listOf("Lower level", "Level 1", "Level 2", "Level 3", "Level 4", "Level 5")

    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        levels.forEachIndexed { index, level ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(MaterialTheme.colors.surface)
                    .clickable { navController.navigate("EachLevelGalleryListScreen/$index") }
            ) {
                Text(
                    text = AnnotatedString(level),
                    modifier = Modifier
                        .padding(20.dp)
                )
            }
        }

        Image(
            painter = painterResource(id = R.drawable.harvard_museum_of_natural_history),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
    }
}

