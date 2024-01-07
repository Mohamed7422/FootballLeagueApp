package com.example.footballleagueapp.data.model.competitions_list_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class CurrentSeason(
    val currentMatchday: Int?,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Winner?
):Parcelable