@file:OptIn(ExperimentalComposeUiApi::class)

package com.cybrarist.islamquiz.pages.qurangames

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cybrarist.islamquiz.R
import com.cybrarist.islamquiz.components.PartSelector
import com.cybrarist.islamquiz.components.QuestionCard
import com.cybrarist.islamquiz.components.SelectPartsButton
import com.cybrarist.islamquiz.viewmodels.GuessAyaNumberViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessNumberOfAyaPage(
    view_model: GuessAyaNumberViewModel= hiltViewModel(),
    dark_theme : Boolean= isSystemInDarkTheme()

){

    view_model.check_theme(dark_theme = dark_theme)

    //Color Options
    val text_color by view_model.text_color.observeAsState()
    val options_color by view_model.options_color.observeAsState()
    val default_background by view_model.default_background_color.observeAsState()



    val random_ayah by view_model.random_ayah.observeAsState()
    val selected_parts by view_model.selected_parts.observeAsState(emptyArray())
    val all_parts by view_model.all_parts.observeAsState(emptyList())
    val current_score by view_model.current_score.observeAsState(0)
    val color by view_model.options_color.observeAsState()
    var select_part_button by remember {  mutableStateOf(false) }

    var answer by remember { mutableStateOf("")}


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        SelectPartsButton(onClick = { select_part_button = ! select_part_button}, select_part_button = select_part_button )
        if (select_part_button)
            PartSelector(
                parts = all_parts.toList(),
                selected_parts =  selected_parts,
                background_color = options_color!!,
                text_color = text_color!!,
                update_function = {
                    view_model.update_part(it)
                }
            )
        else
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7F),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = stringResource(id = R.string.current_score, current_score))
                QuestionCard(question = random_ayah?.ayah , background_color = default_background , text_color = Color.Black)

            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.5F)
                    .background((options_color?:default_background)!!)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxSize(),
                    textStyle = TextStyle(textAlign = TextAlign.Center),
                    singleLine = true,
                    placeholder = {
                        Text(text = stringResource(id = R.string.enter_your_answer),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        ,)},

                    value = answer,
                    onValueChange = { answer =it},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions (
                        onDone= { view_model.check_answer(answer = answer) ; answer=""}
                    )

                )
            }

        }
    }





}



@Composable
@Preview
fun PreviewGuessNumberOfAyaPage(){

}