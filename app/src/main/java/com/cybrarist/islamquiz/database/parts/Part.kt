package com.cybrarist.islamquiz.database.parts

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("parts")
data class Part(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id:Int=0,
    @ColumnInfo(name = "name")
    val name:String,
)
