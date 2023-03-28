package com.example.harvardartmuseumsproject.navigation

sealed class ScreenRoute(val route: String) {
    object Home: ScreenRoute(route = "HomeScreen")
    object Exhibition: ScreenRoute(route = "ExhibitionScreen")
    object Gallery: ScreenRoute(route = "EachLevelGalleryListScreen")
    object GalleryDetails: ScreenRoute(route = "GalleryListDetailsScreen")
    object FullSizeImage: ScreenRoute(route = "FullSizeImageScreen")
}
