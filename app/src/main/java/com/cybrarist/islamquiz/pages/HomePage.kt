package com.cybrarist.islamquiz.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.cybrarist.islamquiz.components.GameCard
import com.cybrarist.islamquiz.viewmodels.HomePageViewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun HomePage(view_model: HomePageViewModel = viewModel() , nav_controller: NavController){

    val all_games by view_model.games.observeAsState(initial = emptyList())

    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 10.dp , vertical = 30.dp)

        ){

        items(all_games){
                GameCard(game=it, nav_controller = nav_controller)
        }
    }

}

//@Composable
//@Preview
//fun HomePagePreview(){
//    HomePage(rememberB)
//}