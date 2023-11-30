package com.cybrarist.islamquiz.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.cybrarist.islamquiz.database.parts.Part


@Composable
fun PartSelector(
    parts: List<Part>,
    selected_parts: Array<Boolean>,
    update_function: (id: Int)->Unit,
    background_color :Color,
    text_color:Color
){

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl)
    {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            items(parts) { part ->
                SelectCard(part = part, selected_part = selected_parts[part.id - 1] , background_color =  background_color, text_color) {onclick->
                    update_function(part.id - 1)
                }
            }
        }
    }

}






@Composable
@Preview
fun PreviewPartSelector(){
//    PartSelector()
}