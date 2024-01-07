package com.example.footballleagueapp.data.model.competitions_list_model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Area(
    val code: String,
    val flag: String?,
    val id: Int,
    val name: String
):Parcelable