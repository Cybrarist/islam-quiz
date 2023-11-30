package com.cybrarist.islamquiz.repositories

import androidx.annotation.WorkerThread
import com.cybrarist.islamquiz.database.games.Game
import com.cybrarist.islamquiz.database.games.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameRepository @Inject constructor (private val game_dao: GameDao) {

    val all_games : Flow<List<Game>> = game_dao.all_games()

    fun get_current_game(game_id: Int): Game {
        return game_dao.get_the_current_game(game_id)
    }

    @WorkerThread
    suspend fun upsert_game(game: Game){
        game_dao.upsert(game)
    }

    @WorkerThread
    suspend fun delete_game(game: Game){
        game_dao.delete(game)
    }


    @WorkerThread
    suspend fun update_game(game: Game){
        game_dao.upsert(game = game)
    }
}