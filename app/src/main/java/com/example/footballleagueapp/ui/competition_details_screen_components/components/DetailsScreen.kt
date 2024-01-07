package com.example.footballleagueapp.ui.competition_details_screen_components.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.footballleagueapp.R
import com.example.footballleagueapp.ui.utils.isNetworkAvailable




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController,

                  viewModel: DetailsViewModel = hiltViewModel()
) {
    val competitionDetailsState = viewModel.competitionDetailsState.value
    val competitionDetails = competitionDetailsState.competitionDetails

    val context  = LocalContext.current

    val backgroundImage: Painter = painterResource(id = R.drawable.soccer_ic)

     Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
         


         Image(
             painter = backgroundImage,
             contentDescription = stringResource(R.string.background_image),
             modifier = Modifier.fillMaxSize(),
             contentScale = ContentScale.Crop
         )
        Column {
            TopAppBar(title = { Text(text = "Details Screen") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack()}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")

                    }
                }
            )

            Card(
                colors = CardDefaults.cardColors(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                elevation = CardDefaults.cardElevation(4.dp)
                ) {
                competitionDetails?.currentSeason?.let { currentSeason ->

                    CurrentSeasonItem(currentSeason)

                }
            }
            Divider(thickness = 2.dp)

            Text(modifier = Modifier.padding(10.dp),
                text = "Seasons",
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 22.sp).copy(Color.White))
            LazyColumn() {

                if (competitionDetails != null) {
                    items(competitionDetails.seasons) { season ->
                        if (season != competitionDetails.seasons[0]) {
                            SeasonItem(season)
                        }

                    }
                }
            }

        }


        if (competitionDetailsState.error.isNotBlank()) {

            Column (modifier = Modifier.align(Alignment.Center)){
                Text(
                    text = stringResource(R.string.check_your_network),
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)

                )
                Spacer(modifier = Modifier.height(16.dp))
                Button( modifier = Modifier.align(Alignment.CenterHorizontally)
                    , colors = ButtonDefaults.buttonColors(Color.Green),
                    onClick = {
                        if (isNetworkAvailable(context)) {
                            // Attempt to reload data
                            viewModel.loadData() // Implement this method in your ViewModel
                        }
                    }) {
                    Text(text = stringResource(R.string.retry), color = Color.Black)
                }
            }

        }

        if (competitionDetailsState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        }
    }
}
