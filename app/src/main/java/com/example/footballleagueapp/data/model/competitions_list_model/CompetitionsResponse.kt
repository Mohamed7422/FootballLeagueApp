package com.example.footballleagueapp.data.model.competitions_list_model

data class CompetitionsResponse(
    val competitions: List<Competition>,
    val count: Int,
    val filters: Filters
)