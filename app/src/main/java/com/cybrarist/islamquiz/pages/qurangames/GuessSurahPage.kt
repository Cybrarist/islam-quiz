package com.cybrarist.islamquiz.pages.qurangames

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cybrarist.islamquiz.R
import com.cybrarist.islamquiz.components.PartSelector
import com.cybrarist.islamquiz.components.QuestionCard
import com.cybrarist.islamquiz.components.SelectPartsButton
import com.cybrarist.islamquiz.ui.MyColors
import com.cybrarist.islamquiz.viewmodels.GuessSoraPageViewModel

@Composable
fun GuessSurahPage (view_model : GuessSoraPageViewModel, dark_theme: Boolean = isSystemInDarkTheme()) {


    //Theme Option 00
    view_model.check_theme(dark_theme)

    //Color Options
    val text_color by view_model.text_color.observeAsState()
    val options_color by view_model.options_color.observeAsState()
    val default_background by view_model.default_background_color.observeAsState()

    val all_parts by view_model.all_parts.observeAsState(emptyList())
    val random_aya by  view_model.random_ayah.observeAsState()
    val all_answers by view_model.all_answers.observeAsState(emptyList())
    val current_score by view_model.current_score.observeAsState(0)

    val buttons_enabled by view_model.buttons_enabled.observeAsState(true)
    val selected_parts by view_model.selected_parts.observeAsState(emptyArray())
    var select_part_button by remember { mutableStateOf(false) }


    val card_shape= RoundedCornerShape(CornerSize(20.dp))


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
                update_function = {
                    view_model.update_part(it)
                },
                background_color = options_color!!,
                text_color = text_color!!
            )
        else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5F),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(text = stringResource(id = R.string.current_score, current_score))

                    QuestionCard(question = random_aya?.ayah , background_color = MyColors.LightAppBackground , text_color = Color.Black)
                }

                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxHeight(0.8F)
                        .heightIn(200.dp),

                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    //            contentPadding = PaddingValues(horizontal = 10.dp , vertical = 30.dp),
                ) {
                    items(all_answers.toList() ?: emptyList()) { answer ->
                        Button(
                            enabled = buttons_enabled!!,
                            onClick = { view_model.check_answer_if_correct(answer.id) },
                            modifier = Modifier
                                .heightIn(min = 100.dp)
                                .shadow(5.dp, card_shape)
                                .clip(card_shape)
                            ,
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(
                                disabledContainerColor = options_color!!,
                                containerColor = default_background!!

                            ),
                        ) {
                            Text(
                                text = answer.name,
                                fontSize = 20.sp,
                                color =text_color!!
                            )
                        }
                    }
                }
        }
    }
}




@Composable
@Preview
fun GuessSoraPagePreview(){
    GuessSurahPage(hiltViewModel())
}