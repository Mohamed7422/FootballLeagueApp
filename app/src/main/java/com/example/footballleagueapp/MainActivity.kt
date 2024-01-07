package com.example.footballleagueapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.footballleagueapp.ui.Screens
import com.example.footballleagueapp.ui.competition_details_screen_components.components.DetailsScreen
import com.example.footballleagueapp.ui.competitions_screen_components.components.CompetitionsScreen
import com.example.footballleagueapp.ui.theme.FootballLeagueAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      window.setFlags(WindowManager.LayoutParams.FLAG_SECURE,WindowManager.LayoutParams.FLAG_SECURE)
         setContent {
            FootballLeagueAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screens.CompetitionsListScreen.route){

                      composable(
                          route = Screens.CompetitionsListScreen.route
                      ){
                          CompetitionsScreen(navController)
                      }

                        composable(route = Screens.CompetitionItemScreen.route+"/{competitionCode}"){
                            DetailsScreen(navController)
                        }
                    }


                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FootballLeagueAppTheme {
        Greeting("Android")
    }
}