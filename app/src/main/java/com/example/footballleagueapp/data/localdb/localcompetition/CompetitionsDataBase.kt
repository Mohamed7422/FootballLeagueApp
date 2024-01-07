package com.example.footballleagueapp.data.localdb.localcompetition

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.data.model.competition_info_model.SeasonsTypeConverter
import com.example.footballleagueapp.data.model.competitions_list_model.AreaTypeConverter
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.data.model.competitions_list_model.CurrentSeasonTypeConverter


@Database(entities = [Competition::class,CompetitionDetailsResponse::class], version = 1, exportSchema = false)
@TypeConverters(AreaTypeConverter::class,CurrentSeasonTypeConverter::class,SeasonsTypeConverter::class)
abstract class CompetitionsDataBase:RoomDatabase() {
    abstract fun competitionsDao() : CompetitionDao
}