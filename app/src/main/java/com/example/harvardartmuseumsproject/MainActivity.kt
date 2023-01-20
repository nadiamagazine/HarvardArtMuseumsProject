package com.example.harvardartmuseumsproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                        ExhibitionScreen(navController = navController)
                    }
                    composable("LowerLevelDetailsScreen") {
                        LowerLevelDetailsScreen(navController = navController)
                    }
                    composable("LevelOneDetailsScreen") {
                        LevelOneDetailsScreen(navController = navController)
                    }
                    composable("LevelTwoDetailsScreen") {
                        LevelTwoDetailsScreen(
                            navController = navController)
                    }
                    composable("LevelThreeDetailsScreen") {
                        LevelThreeDetailsScreen(navController = navController)
                    }
                    composable("LevelFourDetailsScreen") {
                        LevelFourDetailsScreen(navController = navController)
                    }
                    composable("LevelFiveDetailsScreen") {
                        LevelFiveDetailsScreen(navController = navController)
                    }
                }
            }
        }
        Timber.plant(Timber.DebugTree())
    }
}
