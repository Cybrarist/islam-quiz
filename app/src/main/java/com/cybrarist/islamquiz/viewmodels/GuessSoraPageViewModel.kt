package com.cybrarist.islamquiz.viewmodels

import android.content.res.Resources.Theme
import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.cybrarist.islamquiz.database.ayat.Ayah
import com.cybrarist.islamquiz.database.games.Game
import com.cybrarist.islamquiz.database.parts.Part
import com.cybrarist.islamquiz.database.sowar.Surah
import com.cybrarist.islamquiz.repositories.AyahRepository
import com.cybrarist.islamquiz.repositories.GameRepository
import com.cybrarist.islamquiz.repositories.PartRepository
import com.cybrarist.islamquiz.repositories.SurahRepository
import com.cybrarist.islamquiz.ui.MyColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class GuessSoraPageViewModel @Inject constructor (private val surah_repository: SurahRepository,
                                                  private val ayah_repository:AyahRepository ,
                                                  private val game_repository: GameRepository,
                                                  private val part_rep : PartRepository) : ViewModel()
{
    //UI Settings
    var default_background_color : MutableLiveData<Color> = MutableLiveData(MyColors.LightAppBackground)
    var text_color : MutableLiveData<Color> = MutableLiveData()
    var options_color: MutableLiveData<Color> = MutableLiveData()
    var answer_right_color: MutableLiveData<Color> = MutableLiveData()
    var answer_wrong_color: MutableLiveData<Color> = MutableLiveData()


    var current_score: MutableLiveData<Int> = MutableLiveData(0);
    var current_game: Game? = null
    var random_ayah: MutableLiveData<Ayah> = MutableLiveData()
    var all_parts: LiveData<List<Part>> = part_rep.all_parts.asLiveData()
    var selected_parts : MutableLiveData<Array<Boolean>> = MutableLiveData( Array(30) { false})
    var selected_parts_ids : MutableList<Int> = ArrayList()
    var buttons_enabled: MutableLiveData<Boolean> = MutableLiveData(true)
    var all_answers: LiveData<List<Surah>> = random_ayah.map {
        var temp = mutableListOf<Surah>()
        runBlocking {
            val preparing_soras= async {
                temp.add(surah_repository.correct_surah(random_ayah.value!!.surah_id))
                temp.addAll(surah_repository.wrong_sowar(random_ayah.value!!.surah_id, selected_parts_ids))
                Log.d("all answers" , temp.toString())
                temp.shuffle()
            }
            return@runBlocking temp
        }
    }



    init {
        // get the game once the page started
        viewModelScope.launch (Dispatchers.IO) {
            current_game=game_repository.get_current_game(1)
        }
        //get the first question
        refresh_question()
    }

    fun refresh_question() {
        //get the random question

        Log.d("selected Parts", selected_parts_ids.toString())
        viewModelScope.launch (Dispatchers.IO) {
            if (selected_parts_ids.size == 0 )
                random_ayah.postValue(ayah_repository.get_random_ayah())
            else
                random_ayah.postValue(ayah_repository.get_random_ayah(selected_parts_ids))
        }
    }

    fun update_part(index: Int){
        selected_parts.value!![index]= !selected_parts.value!![index]
        selected_parts_ids= ArrayList()
        //update the selected ids
        selected_parts.value?.mapIndexed(){ index, it ->
            if (it)
                selected_parts_ids.add(index+1)
        }
        //push the update to frontend
        selected_parts.postValue(selected_parts.value?.clone() as? Array<Boolean>)
        //get new question depending on the requirements
        refresh_question()
    }


    fun check_answer_if_correct(answer: Int){
        buttons_enabled.postValue(false)
        var temp_color=text_color.value
        text_color.postValue(Color.White)
        if (answer == random_ayah.value!!.surah_id) {
            Log.d("changing the options color" , "correct")
            options_color.postValue(answer_right_color.value)
            current_score.postValue(current_score.value!! + 1)
        }
        else {

            options_color.postValue(answer_wrong_color.value)
            if (current_game!!.high_score < current_score.value!!) {
                current_game!!.high_score= current_score.value!!
                viewModelScope.launch (Dispatchers.IO){
                    game_repository.upsert_game(current_game!!)
                }
            }
            current_score.value=0;
        }

        viewModelScope.launch {

            delay(400) // Delay for
            refresh_question()
            options_color.postValue(default_background_color.value)
            text_color.postValue(temp_color!!)
            buttons_enabled.postValue(true)
        }
    }



    fun check_theme(dark_theme: Boolean){
        if (dark_theme && buttons_enabled.value!!){
            text_color.postValue(Color.White)
            default_background_color.postValue(Color.DarkGray)
            options_color.postValue(Color.DarkGray)
            answer_right_color.postValue(MyColors.GreenPrimary)
            answer_wrong_color.postValue(MyColors.RedDark)
        } else if(!dark_theme && buttons_enabled.value!!){
            text_color.postValue(Color.Black)
            default_background_color.postValue(Color.White)
            options_color.postValue(Color.White)
            answer_right_color.postValue(MyColors.GreenPrimary)
            answer_wrong_color.postValue(MyColors.RedLight)
        }
    }
}

