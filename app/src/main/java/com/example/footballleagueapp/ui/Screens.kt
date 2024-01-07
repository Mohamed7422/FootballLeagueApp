package com.example.footballleagueapp.ui

sealed class Screens(val route:String){
    object CompetitionsListScreen:Screens("competitions_List")
    object CompetitionItemScreen:Screens("competition_Item")
}
