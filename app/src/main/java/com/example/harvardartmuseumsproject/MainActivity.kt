package com.example.harvardartmuseumsproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                        ExhibitionScreen(navController = navController)
                    }
                    composable("LowerLevelGalleryListScreen") {
                        LowerLevelGalleryListScreen(navController = navController)
                    }
                    composable("LevelOneGalleryListScreen") {
                        LevelOneGalleryListScreen(navController = navController)
                    }
                    composable("LevelTwoGalleryListScreen") {
                        LevelTwoGalleryListScreen(
                            navController = navController)
                    }
                    composable("LevelThreeGalleryListScreen") {
                        LevelThreeGalleryListScreen(navController = navController)
                    }
                    composable("LevelFourGalleryListScreen") {
                        LevelFourGalleryListScreen(navController = navController)
                    }
                    composable("LevelFiveGalleryListScreen") {
                        LevelFiveGalleryListScreen(navController = navController)
                    }
                }
            }
        }
        Timber.plant(Timber.DebugTree())
    }
}
