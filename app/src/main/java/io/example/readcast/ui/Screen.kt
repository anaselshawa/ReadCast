package io.example.readcast.ui

sealed class Screen(val rout: String) {
    object Home: Screen("home_screen")
    object Search: Screen("search_screen")
    object Setting: Screen("setting_screen")
}