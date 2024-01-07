package com.example.footballleagueapp.data.model.competitions_list_model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "competition")
data class Competition(
    @PrimaryKey
    val id: Int,
    val area: Area,
    val code: String?,
    val currentSeason: CurrentSeason?,
    val emblem: String?,
    val lastUpdated: String,
    val name: String,
    val numberOfAvailableSeasons: Int,
    val plan: String?,
    val type: String
):Parcelable

class AreaTypeConverter{

    @SuppressLint("SuspiciousIndentation")
    @TypeConverter
    fun fromArea(area: Area?):String?{
     val gson = Gson()
        return gson.toJson(area)

    }
    @SuppressLint("SuspiciousIndentation")
    @TypeConverter
    fun toArea(areaString:String):Area?{
      val json= Gson()
        return json.fromJson(areaString,Area::class.java)
    }
}

class CurrentSeasonTypeConverter{

    @TypeConverter
    fun fromCurrentSeason(currentSeason: CurrentSeason?):String?{
        val gson = Gson()
        return gson.toJson(currentSeason)

    }
    @TypeConverter
    fun toCurrentSeason(currentSeasonString:String):CurrentSeason?{
        val json= Gson()
        return json.fromJson(currentSeasonString,CurrentSeason::class.java)
    }
}




