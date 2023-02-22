package com.example.harvardartmuseumsproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.harvardartmuseumsproject.ui.theme.HarvardArtMuseumsProjectTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HarvardArtMuseumsProjectTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "HomeScreen"
                ) {
                    composable("HomeScreen") {
                        HomeScreen(navController = navController)
                    }
                    composable("ExhibitionScreen") {
                        ExhibitionScreen(
                            navController = navController)
                    }
                    composable("EachLevelGalleryListScreen/{level}",
                        arguments = listOf(
                            navArgument("level")
                            { type = NavType.IntType }
                        )
                    ) {
                        val level = it.arguments?.getInt("level")
                        if (level != null) {
                            EachLevelGalleryListScreen(
                                navController = navController,
                                level = level
                            )
                        }

                    }
                    composable("GalleryListDetailsScreen/{id}",
                        arguments = listOf(
                            navArgument("id")
                            { type = NavType.StringType }
                        )
                    ) {
                        val galleryId = it.arguments?.getString("id")
                        if (galleryId != null) {
                            GalleryListDetailsScreen(
                                navController = navController,
                                id = galleryId
                            )
                        }

                    }

                }
            }
        }
        Timber.plant(Timber.DebugTree())
    }
}
