package com.example.footballleagueapp.ui.competition_details_screen_components

import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse

data class CompetitionDetailsState(
    val isLoading:Boolean  = false,
    val competitionDetails:CompetitionDetailsResponse ?= null,
    val error :String = ""
)
