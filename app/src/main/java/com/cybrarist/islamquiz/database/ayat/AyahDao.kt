package com.cybrarist.islamquiz.database.ayat

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AyahDao {

    @Query("select * from ayat order by RANDOM() LIMIT 1")
    fun select_random() : Ayah

    @Query("select * from ayat where part_id IN (:parts_selected) ORDER BY  RANDOM() LIMIT 1")
    fun select_random_within(parts_selected: List<Int>): Ayah
}