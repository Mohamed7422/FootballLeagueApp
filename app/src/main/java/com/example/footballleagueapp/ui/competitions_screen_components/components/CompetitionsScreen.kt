package com.example.footballleagueapp.ui.competitions_screen_components.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.footballleagueapp.R
import com.example.footballleagueapp.ui.Screens
import com.example.footballleagueapp.ui.competitions_screen_components.CompetitionsViewModel
import com.example.footballleagueapp.ui.utils.isNetworkAvailable


@SuppressLint("SuspiciousIndentation")
@Composable
fun CompetitionsScreen (
    navController: NavController,
 viewModel: CompetitionsViewModel = hiltViewModel()
){

 val competitionsState = viewModel.competitionsState.value
    val context = LocalContext.current

    val backgroundImage: Painter = painterResource(id = R.drawable.soccer_ic)

   Box(modifier = Modifier.fillMaxSize()
       ){

       Image(
           painter = backgroundImage,
           contentDescription = stringResource(R.string.background_image),
           modifier = Modifier.fillMaxSize(),
           contentScale = ContentScale.Crop
       )

      LazyColumn(){
                       items(competitionsState.competitions){ competition ->
                           println("CompetitionCodeFromScreen ${competition.code}")

                           CompetitionItem(competition = competition,

                                       onItemClick = {
                                           navController.navigate(Screens.CompetitionItemScreen.route+"/${competition.code}")
                                       } )
                       }

      }


       if (competitionsState.error.isNotBlank()){

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

       if (competitionsState.isLoading){
          CircularProgressIndicator(modifier = Modifier.align(Alignment.Center), color = Color.Green)
       }
   }

}



