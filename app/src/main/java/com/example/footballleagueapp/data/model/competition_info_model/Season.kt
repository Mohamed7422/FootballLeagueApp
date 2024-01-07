package com.example.footballleagueapp.data.model.competition_info_model

data class Season(
    val currentMatchday: Int?,
    val endDate: String,
    val id: Int,
    val stages: List<String>,
    val startDate: String,
    val winner: Winner?
)