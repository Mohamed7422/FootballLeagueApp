package com.example.footballleagueapp.data.localdb.localcompetition

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.data.model.competitions_list_model.Competition


@Dao
interface CompetitionDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitionInDb(competition: Competition)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetitions(competitions: List<Competition>)

    @Query("SELECT * FROM competition")
    suspend fun getCompetitionsList():List<Competition>

    @Query("SELECT * FROM competition WHERE id = :id")
    suspend fun getCompetition(id:Int?):Competition

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCompetitionsList(competitionsList:List<Competition>)


    @Query("DELETE  FROM competition")
    suspend fun deleteCompetitionsList()

    /*____________________________________________________________________*/
    /*_____________________Competition Details CRUF___________________________*/

    @Upsert
    suspend fun insertCompetitionDetailsInDb(competitionDetails: CompetitionDetailsResponse)

    @Query("SELECT * FROM competition_details WHERE code = :code")
    suspend fun getCompetitionDetailsFromDb(code: String):CompetitionDetailsResponse

    @Query("DELETE  FROM competition_details")
    suspend fun deleteCompetitionDetails()



}