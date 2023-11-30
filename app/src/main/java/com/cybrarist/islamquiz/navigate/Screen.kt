package com.cybrarist.islamquiz.navigate

sealed class Screen (val route: String){
    object MainScreen: Screen("homepage")
    object GuessSurah: Screen("guess_sora")
    object GuessAyaNumber: Screen("guess_aya_number")
//    object GuessPart: Screen("guess_part")
//    object GuessTotalAya: Screen("guess_total_aya")

}
