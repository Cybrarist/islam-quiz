package com.cybrarist.islamquiz.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cybrarist.islamquiz.database.ayat.Ayah
import com.cybrarist.islamquiz.database.ayat.AyahDao
import com.cybrarist.islamquiz.database.games.Game
import com.cybrarist.islamquiz.database.games.GameDao
import com.cybrarist.islamquiz.database.parts.Part
import com.cybrarist.islamquiz.database.parts.PartDao
import com.cybrarist.islamquiz.database.sowar.StatusConverter
import com.cybrarist.islamquiz.database.sowar.Surah
import com.cybrarist.islamquiz.database.sowar.SurahDao

@Database(
    entities = [Game::class, Part::class , Surah::class , Ayah::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(StatusConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun game_dao():GameDao
    abstract fun part_dao(): PartDao
    abstract fun sora_dao(): SurahDao
    abstract fun ayah_dao(): AyahDao
}

