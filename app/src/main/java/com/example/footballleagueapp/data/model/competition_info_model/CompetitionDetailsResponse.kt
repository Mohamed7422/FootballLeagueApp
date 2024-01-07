package com.example.footballleagueapp.data.model.competition_info_model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.footballleagueapp.data.model.competitions_list_model.Area
import com.example.footballleagueapp.data.model.competitions_list_model.CurrentSeason
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


@Entity(tableName = "competition_details")
data class CompetitionDetailsResponse(
    @PrimaryKey
    val id: Int,
    val area: Area,
    val code: String,
    val currentSeason: CurrentSeason,
    val emblem: String,
    val lastUpdated: String,
    val name: String,
    val seasons: List<Season>,
    val type: String
)


class SeasonsTypeConverter{
    private val gson = Gson()

    @TypeConverter
    fun fromSeasonList(seasons: List<Season>?): String{
        return  gson.toJson(seasons)
    }

    @TypeConverter
    fun toSeasonList(seasonString:String?): List<Season> {
        if (seasonString == null) return emptyList()
        val type  = object : TypeToken<List<Season>>() {}.type
        return  gson.fromJson(seasonString, type)
     }
    }


