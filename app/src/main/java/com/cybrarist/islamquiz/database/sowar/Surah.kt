package com.cybrarist.islamquiz.database.sowar

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.cybrarist.islamquiz.database.parts.Part
import com.cybrarist.islamquiz.enums.RevelationTypeEnum

@Entity("sowar" , foreignKeys = [ForeignKey(Part::class , ["id"], ["part_id"] )], indices = [Index("part_id")] )
data class Surah(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name:String,
    val part_id:Int,
    val revelation_type : RevelationTypeEnum
)


class StatusConverter {
    @TypeConverter
    fun fromRevelationType(type: RevelationTypeEnum): Int {
        return type.value
    }

    @TypeConverter
    fun toRevelationType(value: Int): RevelationTypeEnum {
        return when (value) {
            1 -> RevelationTypeEnum.MECCAN
            2 -> RevelationTypeEnum.MEDINAN
            else -> throw IllegalArgumentException("Invalid status value: $value")
        }
    }
}