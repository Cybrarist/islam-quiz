package com.cybrarist.islamquiz.components

import android.provider.CalendarContract
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cybrarist.islamquiz.database.parts.Part
import com.cybrarist.islamquiz.ui.MyColors

@Composable
fun SelectCard(
    part: Part,
    selected_part: Boolean,
    background_color :Color,
    text_color: Color,
    onClick: (Any?) -> Unit,

    ){
    Button (
        modifier = Modifier
            .heightIn(min = 60.dp)
            .shadow(5.dp ,  RoundedCornerShape(CornerSize(30.dp)))
            .clip(RoundedCornerShape(CornerSize(20.dp)))
                ,
        colors = ButtonDefaults.buttonColors(
            containerColor = if(selected_part) MyColors.GreenPrimary else background_color ,
        ),
        onClick={ onClick(""); } ,
    ) {
        Text(modifier=Modifier.fillMaxSize(),
            text = part.name,
            textAlign = TextAlign.Center,
            color = if (selected_part) Color.White else text_color)

    }
}



@Composable
@Preview(showBackground = true)

fun PreviewSelectCard(){
    SelectCard(
        Part(1 , "test part"),
        selected_part = true,
        background_color = Color.DarkGray,
        text_color= Color.White,
        onClick = {}
    )
}