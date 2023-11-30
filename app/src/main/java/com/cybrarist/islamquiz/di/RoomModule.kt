package com.cybrarist.islamquiz.di

import android.content.Context
import androidx.room.Room
import com.cybrarist.islamquiz.database.AppDatabase
import com.cybrarist.islamquiz.database.ayat.AyahDao
import com.cybrarist.islamquiz.database.games.GameDao
import com.cybrarist.islamquiz.database.parts.PartDao
import com.cybrarist.islamquiz.database.sowar.SurahDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    fun provide_database(@ApplicationContext context: Context) : AppDatabase {
        return  Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "Quran_Quiz_DB")
            .createFromAsset("database/database.db")
            .build()
    }
    @Provides
    fun provide_game_dao(app_database: AppDatabase) : GameDao {
        return app_database.game_dao()
    }

    @Provides
    fun provide_part_dao(app_database: AppDatabase) : PartDao{
        return app_database.part_dao()
    }
    @Provides
    fun provide_sora_dao(app_database: AppDatabase) : SurahDao {
        return app_database.sora_dao()
    }

    @Provides
    fun provide_ayah_dao(app_database: AppDatabase) : AyahDao{
        return app_database.ayah_dao()
    }
}