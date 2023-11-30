package com.cybrarist.islamquiz.database.parts

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PartDao {
    @Query("select * from parts")
    fun  all_parts() : Flow<List<Part>>

    @Query("select * from parts where id=:id")
    suspend fun select(id:Int) : Part

    @Query("select * from parts where id!=:id order by RANDOM() LIMIT 3")
    fun select_3_random(id:Int?) : List<Part>


}