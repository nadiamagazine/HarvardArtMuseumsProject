package com.example.harvardartmuseumsproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.harvardartmuseumsproject.model.Gallery
import com.example.harvardartmuseumsproject.model.Records

@Composable
fun ExhibitionScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
        ) {
            ClickableText(
                text = AnnotatedString("Lower level"),
                onClick = { navController.navigate("EachLevelGalleryListScreen") },
                modifier = Modifier
                    .padding(20.dp)
//                style = TextStyle(
//                    color = Black,
//                    fontSize = 30.sp,
//                    fontFamily = FontFamily.Bold
//                )
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
                .clickable { navController.navigate("EachLevelGalleryListScreen") }
        ) {
            Text(
                text = AnnotatedString("Level 1"),
                modifier = Modifier
                    .padding(20.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
                .clickable { navController.navigate("EachLevelGalleryListScreen") }

        ) {
            Text(
                text = AnnotatedString("Level 2"),
                modifier = Modifier
                    .padding(20.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
                .clickable { navController.navigate("EachLevelGalleryListScreen") }

        ) {
            Text(
                text = AnnotatedString("Level 3"),
                modifier = Modifier
                    .padding(20.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
                .clickable { navController.navigate("EachLevelGalleryListScreen") }

        ) {
            Text(
                text = AnnotatedString("Level 4"),
                modifier = Modifier
                    .padding(20.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colors.surface)
                .clickable { navController.navigate("EachLevelGalleryListScreen") }

        ) {
            Text(
                text = AnnotatedString("Level 5"),
                modifier = Modifier
                    .padding(20.dp)
            )
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

