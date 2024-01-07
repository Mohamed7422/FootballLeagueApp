package com.example.footballleagueapp.ui.competition_details_screen_components.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballleagueapp.data.model.competition_info_model.Season
import com.example.footballleagueapp.data.model.competitions_list_model.CurrentSeason
import com.example.footballleagueapp.ui.utils.LoadingImage


@Composable
fun SeasonItem(season: Season) {

    season.winner?.let {

        Card(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Column(modifier = Modifier.padding(10.dp)) {


                CreateItemAsRow("Start Date : ", season.startDate)

                CreateItemAsRow("End Date : ", season.endDate)

                LoadingImage(path =  it.crest)


                CreateItemAsRow("Winner Name : ", it.name)

                CreateItemAsRow("Stadium : ", it.venue)

                CreateItemAsRow("Founded at : ", it.founded.toString())

            }

        }
    }
}

@Composable
fun CurrentSeasonItem(currentSeason: CurrentSeason) {
    Column(modifier = Modifier.padding(10.dp)) {

        Text(text = "The Current Season", fontSize = 22.sp)
        CreateItemAsRow("Start Date : ", value = currentSeason.startDate)
        CreateItemAsRow("End Date : ", value = currentSeason.endDate)

    }

}

@Composable
fun CreateItemAsRow(label: String, value: String?) {
    value?.let {
        Row {
            Text(text = label)
            Text(text = it)
        }
    }

}
