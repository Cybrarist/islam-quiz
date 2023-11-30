package com.cybrarist.islamquiz.database.sowar

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SurahDao {
    @Query("select * from sowar")
    fun  all_sowar() : Flow<List<Surah>>

    @Query("select * from sowar where id=:id")
    suspend fun get_single_sora (id:Int) : Surah

    @Query("select * from sowar where id!=:id order by RANDOM() LIMIT 3")
    suspend fun get_wrong_soras(id:Int?) : List<Surah>

    @Query("select * from sowar where id!=:id and part_id in (:selected_parts) order by RANDOM() LIMIT 3")
    suspend fun get_wrong_soras_with_part(id:Int? , selected_parts:List<Int>) : List<Surah>


}