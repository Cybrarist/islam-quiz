package com.cybrarist.islamquiz.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cybrarist.islamquiz.R

@Composable
fun SelectPartsButton(
    onClick: ()->Unit,
    select_part_button: Boolean
){
    Row (
        modifier= Modifier.padding(vertical = 15.dp) ,
        horizontalArrangement = Arrangement.Center,
    ) {
        OutlinedButton(onClick= onClick) {
            Text(text = if(select_part_button)  stringResource(id = R.string.go_back) else stringResource(
                R.string.choose_part) )
        }
    }

}




@Composable
@Preview
fun PreviewSelectPartsButton(){
//    SelectPartsButton(){
//
//    }
}