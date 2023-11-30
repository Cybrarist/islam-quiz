package com.cybrarist.islamquiz.database.games

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.cybrarist.islamquiz.enums.GameTypeEnum
import com.cybrarist.islamquiz.enums.RevelationTypeEnum


@Entity("games")
data class Game(
    @PrimaryKey(autoGenerate = true)
    val id:Int =0,
    val name: String,
    var high_score:Int=0,
    val game_type: Int?

)


class GameTypeConverter {
    @TypeConverter
    fun fromRevelationType(type: GameTypeEnum): Int {
        return type.value
    }

    @TypeConverter
    fun toRevelationType(value: Int): GameTypeEnum {
        return when (value) {
            1 -> GameTypeEnum.Quran
            2 -> GameTypeEnum.Sunnah
            else -> throw IllegalArgumentException("Invalid status value: $value")
        }
    }
}