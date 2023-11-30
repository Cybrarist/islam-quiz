package com.cybrarist.islamquiz.repositories

import androidx.annotation.WorkerThread
import com.cybrarist.islamquiz.database.sowar.Surah
import com.cybrarist.islamquiz.database.sowar.SurahDao
import java.util.concurrent.Flow
import javax.inject.Inject

class SurahRepository @Inject constructor(private val surah_dao: SurahDao) {


    var all_sowar  =surah_dao.all_sowar()
    @WorkerThread
    suspend fun correct_surah(id: Int) : Surah{
        return surah_dao.get_single_sora(id)
    }
    @WorkerThread
    suspend fun wrong_sowar(id: Int , selected_parts: List<Int>) : List<Surah>{

        if (selected_parts.size > 0)
            return surah_dao.get_wrong_soras_with_part(id , selected_parts)

        return surah_dao.get_wrong_soras(id)
    }

}