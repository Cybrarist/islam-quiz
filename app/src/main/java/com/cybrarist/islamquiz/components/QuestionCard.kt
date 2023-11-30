package com.cybrarist.islamquiz.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun QuestionCard(
    question: String?,
    background_color: Color?,
    text_color:Color
){
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        ),
        modifier = Modifier
            .fillMaxHeight(0.8F)
            .fillMaxWidth(0.9F)
            .padding(vertical = 10.dp),

        ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .heightIn(Dp.Unspecified, Dp.Infinity)
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(question ?: "Fetching ...",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                lineHeight = 40.sp
            )
        }


    }
}

@Composable
@Preview
fun QuestionCardPreview(){
    QuestionCard(question = "this is the question you want to answer", Color.White , Color.Black)
}