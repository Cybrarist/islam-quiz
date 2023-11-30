package com.cybrarist.islamquiz.repositories

import androidx.annotation.WorkerThread
import com.cybrarist.islamquiz.database.parts.Part
import com.cybrarist.islamquiz.database.parts.PartDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PartRepository @Inject constructor (private val part_dao: PartDao) {

    var all_parts : Flow<List<Part>> = part_dao.all_parts()
    @WorkerThread
    suspend fun get_part(id : Int): Part {
        return part_dao.select(id)
    }
    @WorkerThread
    suspend fun get_other_parts(id : Int?): List<Part> {
        if (id != null)
            return part_dao.select_3_random(id)
        return emptyList()
    }



}