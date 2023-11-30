package com.cybrarist.islamquiz.helpers

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.cybrarist.islamquiz.R
import com.cybrarist.islamquiz.navigate.Screen

object GameHelpers {
    fun get_resource(id :Int): Int {

        return when (id){
            1 -> R.drawable.quran
            2 ->R.drawable.aya_no
            else -> androidx.core.R.drawable.ic_call_answer
        }
    }

    fun get_route(id: Int) : String {
        return when (id){
            1 -> Screen.GuessSurah.route
            2-> Screen.GuessAyaNumber.route
            else -> {""}
        }
    }
}