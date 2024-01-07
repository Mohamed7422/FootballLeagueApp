package com.example.footballleagueapp.ui.competitions_screen_components.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.ui.utils.LoadingImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompetitionItem(competition: Competition,
                    onItemClick :(Competition)->Unit) {

    Card (elevation = CardDefaults.cardElevation(4.dp),
          modifier = Modifier.padding(15.dp),
          colors = CardDefaults.cardColors(Color.White),
          onClick = {
              onItemClick(competition)
          }

    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),){

            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp) ,
                ){

                LoadingImage(competition.emblem?:"null")
                Spacer(modifier = Modifier.width(2.dp))
                Column (verticalArrangement = Arrangement.SpaceEvenly){
                    Text(text = competition.name,
                        style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = competition.area.name,
                        style = MaterialTheme.typography.bodySmall)

                }
            }


        }
    }

      
   
}



