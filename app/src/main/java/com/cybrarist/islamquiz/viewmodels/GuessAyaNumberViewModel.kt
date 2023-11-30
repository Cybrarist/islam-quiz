package com.cybrarist.islamquiz.viewmodels

import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cybrarist.islamquiz.database.ayat.Ayah
import com.cybrarist.islamquiz.database.games.Game
import com.cybrarist.islamquiz.database.parts.Part
import com.cybrarist.islamquiz.repositories.AyahRepository
import com.cybrarist.islamquiz.repositories.GameRepository
import com.cybrarist.islamquiz.repositories.PartRepository
import com.cybrarist.islamquiz.ui.MyColors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GuessAyaNumberViewModel @Inject constructor(
    private val ayah_repository : AyahRepository ,
    private val part_rep: PartRepository ,
    private val game_repository: GameRepository ,

)  : ViewModel() {

    //UI Settings
    var default_background_color : MutableLiveData<Color> = MutableLiveData(MyColors.LightAppBackground)
    var text_color : MutableLiveData<Color> = MutableLiveData()
    var options_color: MutableLiveData<Color> = MutableLiveData()
    var answer_right_color: MutableLiveData<Color> = MutableLiveData()
    var answer_wrong_color: MutableLiveData<Color> = MutableLiveData()


    var current_score: MutableLiveData<Int> = MutableLiveData(0);
    var current_game: Game? = null
    var random_ayah: MutableLiveData<Ayah> = MutableLiveData()
    val all_parts: LiveData<List<Part>> = part_rep.all_parts.asLiveData()
    var selected_parts : MutableLiveData<Array<Boolean>> = MutableLiveData( Array(30) { false})
    var selected_parts_ids : MutableList<Int> = ArrayList()

    init {
        //get the current game
        viewModelScope.launch (Dispatchers.IO) {
            current_game=game_repository.get_current_game(2)
        }
        //get the question with answers
        refresh_question()
    }

    fun check_answer(answer :String){

        if (!TextUtils.isDigitsOnly(answer)){
            return;
        }



        if (answer != "" && answer.toInt() == random_ayah.value!!.order_in_surah){
            options_color.postValue(answer_right_color.value!!)
            current_score.postValue(current_score.value!!+1)
        }
        else{
            options_color.postValue(answer_wrong_color.value!!)
            current_score.postValue(0)
        }

        viewModelScope.launch (Dispatchers.IO){
            delay(400)
            if (current_score.value!!>current_game!!.high_score)
            {
                current_game!!.high_score= current_score.value!!
                game_repository.upsert_game(current_game!!)
            }

            refresh_question()
            options_color.postValue(default_background_color.value!!)
        }

    }

    fun refresh_question() {

        viewModelScope.launch (Dispatchers.IO) {
            if (selected_parts_ids.size <= 0 )
                random_ayah.postValue(ayah_repository.get_random_ayah())
            else
                random_ayah.postValue(ayah_repository.get_random_ayah(selected_parts_ids))
        }
    }

    fun update_part(index: Int){
        selected_parts.value!![index]= !selected_parts.value!![index]
        selected_parts.value?.mapIndexed(){ index, it ->
            if (it)
                selected_parts_ids.add(index+1)
        }
        selected_parts.postValue(selected_parts.value?.clone() as? Array<Boolean>)
        refresh_question()
    }



    fun check_theme(dark_theme: Boolean){
        if (dark_theme && text_color.value != Color.White){
            text_color.postValue(Color.White)
            default_background_color.postValue(Color.DarkGray)
            options_color.postValue(Color.DarkGray)
            answer_right_color.postValue(MyColors.GreenPrimary)
            answer_wrong_color.postValue(MyColors.RedDark)
        } else if(!dark_theme && text_color.value !=Color.Black){
            text_color.postValue(Color.Black)
            default_background_color.postValue(Color.White)
            options_color.postValue(Color.White)
            answer_right_color.postValue(MyColors.GreenPrimary)
            answer_wrong_color.postValue(MyColors.RedLight)
        }
    }

}