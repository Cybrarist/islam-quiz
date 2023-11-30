package com.cybrarist.islamquiz.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cybrarist.islamquiz.database.games.Game
import com.cybrarist.islamquiz.repositories.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor (private val repository: GameRepository) : ViewModel(){
    var games : LiveData<List<Game>> = repository.all_games.asLiveData()

    fun update_games(game: Game){
        viewModelScope.launch {
            repository.upsert_game(game)
        }
    }
}

