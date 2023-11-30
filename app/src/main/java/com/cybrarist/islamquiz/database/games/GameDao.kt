package com.cybrarist.islamquiz.database.games

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Upsert
    suspend fun upsert(game : Game)
    @Delete
    suspend fun  delete(game: Game)

    @Query("select * from games order by id")
    fun  all_games() : Flow<List<Game>>

    @Query("select * from games where id=:id")
    fun get_the_current_game(id : Int): Game


}