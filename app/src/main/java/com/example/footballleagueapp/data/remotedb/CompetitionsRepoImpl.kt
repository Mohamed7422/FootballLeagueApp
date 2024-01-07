package com.example.footballleagueapp.data.remotedb

import com.example.footballleagueapp.data.localdb.localcompetition.CompetitionDao
import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.data.model.competitions_list_model.CompetitionsResponse
import com.example.footballleagueapp.domain.repository.CompetitionsRepo
import javax.inject.Inject

class CompetitionsRepoImpl @Inject constructor(
    private val competitionDao: CompetitionDao,
    private val apiService: CompetitionsApiService
) : CompetitionsRepo {
    override suspend fun getCompetitions(): CompetitionsResponse {
         return apiService.getCompetitions()
    }

    override suspend fun getCompetitionDetails(code: String): CompetitionDetailsResponse {
        return apiService.getCompetitionDetails(code)
    }

    override suspend fun insertCompetitionInDb(competition: Competition) {
        competitionDao.insertCompetitionInDb(competition)
    }

    override suspend fun insertCompetitionsInDb(competitions: List<Competition>) {
        competitionDao.insertCompetitions(competitions)
    }

    override suspend fun getCompetitionsList(): List<Competition> {
       return competitionDao.getCompetitionsList()
    }

    override suspend fun getCompetition(id: Int?): Competition {
        return competitionDao.getCompetition(id)
    }

    override suspend fun updateCompetitionsList(competitionsList: List<Competition>) {
        competitionDao.updateCompetitionsList(competitionsList)
    }

    override suspend fun deleteCompetitionsList() {
        competitionDao.deleteCompetitionsList()
    }

    override suspend fun insertCompetitionDetailsInDb(competitionDetails: CompetitionDetailsResponse) {
        competitionDao.insertCompetitionDetailsInDb(competitionDetails)
    }

    override suspend fun getCompetitionDetailsFromDb(code: String): CompetitionDetailsResponse {
      return  competitionDao.getCompetitionDetailsFromDb(code)
    }

    override suspend fun deleteCompetitionDetails() {
        competitionDao.deleteCompetitionDetails()
    }



}