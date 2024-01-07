package com.example.footballleagueapp.ui.competitions_screen_components

import com.example.footballleagueapp.data.model.competitions_list_model.Competition

data class CompetitionsState(
    val isLoading:Boolean = false,
    val competitions: List<Competition> = emptyList(),
    val error:String = ""
)
