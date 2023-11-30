package com.cybrarist.islamquiz.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import com.cybrarist.islamquiz.database.ayat.Ayah
import com.cybrarist.islamquiz.database.ayat.AyahDao
import javax.inject.Inject

class AyahRepository @Inject constructor(private val ayah_dao: AyahDao) {


    @WorkerThread
    fun get_random_ayah() : Ayah {
           return ayah_dao.select_random()
    }
    @WorkerThread
    fun get_random_ayah(selected_parts: List<Int>) : Ayah {
        return ayah_dao.select_random_within(selected_parts)
    }
}