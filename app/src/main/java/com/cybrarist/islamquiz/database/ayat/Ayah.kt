package com.cybrarist.islamquiz.database.ayat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.cybrarist.islamquiz.database.parts.Part
import com.cybrarist.islamquiz.database.sowar.Surah

@Entity("ayat" , foreignKeys = [
    ForeignKey(Surah::class , ["id"] , ["surah_id"]),
    ForeignKey(Part::class , ["id"] , ["part_id"]),
],
    indices = [Index(value = ["surah_id", "part_id"])]
)
data class Ayah(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val ayah: String,
    @ColumnInfo("surah_id" , index = true)
    val surah_id: Int,
    val page_no: Int,
    val order_in_surah: Int,
    @ColumnInfo("part_id" , index = true)
    val part_id :Int,
)
