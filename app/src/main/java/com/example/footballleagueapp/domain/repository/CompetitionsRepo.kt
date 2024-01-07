package com.example.footballleagueapp.domain.repository

import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.data.model.competitions_list_model.CompetitionsResponse


interface CompetitionsRepo {

    suspend fun getCompetitions(): CompetitionsResponse
    suspend fun getCompetitionDetails(code:String): CompetitionDetailsResponse


    //Competitions List Data Base Functions

    suspend fun insertCompetitionInDb(competition: Competition)
    suspend fun insertCompetitionsInDb(competitions: List<Competition>)

    suspend fun getCompetitionsList():List<Competition>

    suspend fun getCompetition(id:Int?): Competition

    suspend fun updateCompetitionsList(competitionsList:List<Competition>)

    suspend fun deleteCompetitionsList()

    suspend fun insertCompetitionDetailsInDb(competitionDetails: CompetitionDetailsResponse)
    suspend fun getCompetitionDetailsFromDb(code: String):CompetitionDetailsResponse
    suspend fun deleteCompetitionDetails()




}