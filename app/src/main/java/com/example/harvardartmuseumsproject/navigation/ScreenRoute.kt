package com.example.harvardartmuseumsproject.navigation

sealed class ScreenRoute(val route: String) {
    object Home: ScreenRoute("HomeScreen")
    object Exhibition: ScreenRoute("ExhibitionScreen")
}
