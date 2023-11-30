package com.cybrarist.islamquiz.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.cybrarist.islamquiz.R
import com.cybrarist.islamquiz.ui.MyColors
import com.cybrarist.islamquiz.database.games.Game
import com.cybrarist.islamquiz.helpers.GameHelpers


@Composable
fun GameCard(
    game: Game,
    nav_controller: NavController
){
    val context = LocalContext.current
    val card_shape= RoundedCornerShape(CornerSize(20.dp))

    //default white theme values
    var main_color= MyColors.GreenPrimary
    var text_color=Color.Black
    var background_color= Color.White

    if (isSystemInDarkTheme()){
        text_color=Color.White
        background_color=Color.DarkGray
    }


//
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(6.dp, card_shape )
            .background(background_color)
            .clickable(onClick = {
                nav_controller.navigate(GameHelpers.get_route(game.id))
            })
        ,
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.3f),
            verticalArrangement = Arrangement.Center,

            ){
                Image(
                    painter = painterResource(id = GameHelpers.get_resource(game.id)),
                    contentDescription = "some useful description",
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(1f)
                        .padding(15.dp)
                )
            }
        Column (
            modifier= Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,

        ){
            Text(text = game.name,
                color = text_color ,
                modifier= Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Right
            )

            Text(text =  stringResource(id = R.string.high_score , game.high_score),
                color = main_color ,
                modifier= Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                fontSize = 15.sp,
                textAlign = TextAlign.Right
            )

        }

    }
}

@Composable
@Preview
fun GameCardPreview(){
    GameCard(
        game = Game(
            1,"test game" , 1000 , 1
        ) ,
        rememberNavController()
    )
}
