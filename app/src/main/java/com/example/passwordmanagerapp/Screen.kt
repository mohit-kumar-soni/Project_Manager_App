package com.example.passwordmanagerapp

sealed class Screen(val route : String){
    object HomeScreen : Screen("home_screen")
    object AddScreen : Screen("add_screen")
    object SplashScreen : Screen("splash_screen")

}