package com.example.harvardartmuseumsproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.harvardartmuseumsproject.navigation.ScreenRoute
import com.example.harvardartmuseumsproject.ui.theme.HarvardArtMuseumsProjectTheme
import com.example.harvardartmuseumsproject.viewmodel.EachLevelViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HarvardArtMuseumsProjectTheme {
                NavHost(
                    navController = navController,
                    startDestination = "galleriesGraph"
                ) {
                    galleriesGraph(navController)
                }
            }
        }
    }
}

fun NavGraphBuilder.galleriesGraph(navController: NavController) {
    navigation(startDestination = ScreenRoute.Home.route, route = "galleriesGraph") {
        composable(ScreenRoute.Home.route) {
            HomeScreen(
                onNavigateToExhibitionScreen =
                {
                    navController.navigate(
                        ScreenRoute.Exhibition.route
                    )
                }
            )
        }
        composable(
            route = ScreenRoute.Exhibition.route,
        ) {
            ExhibitionScreen(
                onNavigateToGalleryScreen = { level ->
                    navController.navigate("${ScreenRoute.Gallery.route}/$level")
                }
            )
        }
        composable(
            route = "${ScreenRoute.Gallery.route}/{level}",
            arguments = listOf(
                navArgument("level") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val level = backStackEntry.arguments?.getInt("level")
            if (level != null) {
                EachLevelGalleryListScreen(
                    level = level,
                    onNavigateToGalleryDetailsScreen = { galleryId ->
                        navController.navigate("${ScreenRoute.GalleryDetails.route}/$galleryId")
                    },
                    viewModel = EachLevelViewModel(level = level)
                )
            }
        }
        composable("${ScreenRoute.GalleryDetails.route}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val galleryId = backStackEntry.arguments?.getString("id")
            if (galleryId != null) {
                GalleryListDetailsScreen(
                    id = galleryId,
                    onNavigateToFullSizeImageScreen = { imageId ->
                        navController.navigate("${ScreenRoute.FullSizeImage.route}/$imageId")
                    }
                )
            }

        }
        composable("${ScreenRoute.FullSizeImage.route}/{imageId}",
            arguments = listOf(navArgument("imageId")
            { type = NavType.StringType }
            )
        ) {
            val imageId = it.arguments?.getString("imageId")
            if (imageId != null) {
                FullSizeImageScreen(
                    imageId = imageId
                )
            }
        }

    }
}
