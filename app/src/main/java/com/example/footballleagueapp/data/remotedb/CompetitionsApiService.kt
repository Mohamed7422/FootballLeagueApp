package com.example.footballleagueapp.data.remotedb

import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.data.model.competitions_list_model.CompetitionsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionsApiService {


    @GET("v4/competitions/")
    suspend fun getCompetitions(): CompetitionsResponse

    @GET("v4/competitions/{code}")
    suspend fun getCompetitionDetails(
        @Path("code") code:String
    ):CompetitionDetailsResponse

}