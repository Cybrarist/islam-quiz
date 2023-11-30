package com.cybrarist.islamquiz.navigate

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cybrarist.islamquiz.pages.HomePage
import com.cybrarist.islamquiz.pages.qurangames.GuessNumberOfAyaPage
import com.cybrarist.islamquiz.pages.qurangames.GuessSurahPage
import com.cybrarist.islamquiz.viewmodels.GuessSoraPageViewModel
import com.cybrarist.islamquiz.viewmodels.HomePageViewModel


@Composable

fun setup_navigation(
    nav_controller: NavHostController
){
    NavHost(navController = nav_controller, startDestination =  Screen.MainScreen.route){
        composable(Screen.MainScreen.route){
            backStackEntry ->
            // Creates a ViewModel from the current BackStackEntry
            // Available in the androidx.hilt:hilt-navigation-compose artifact
            val viewModel = hiltViewModel<HomePageViewModel>()
            HomePage(viewModel , nav_controller)
        }

        composable(Screen.GuessSurah.route){
            GuessSurahPage(view_model = hiltViewModel<GuessSoraPageViewModel>())
        }
        composable(Screen.GuessAyaNumber.route){
            GuessNumberOfAyaPage()
        }
    }
}