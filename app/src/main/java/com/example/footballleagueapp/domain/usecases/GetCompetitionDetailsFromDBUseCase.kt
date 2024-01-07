package com.example.footballleagueapp.domain.usecases

import com.example.footballleagueapp.common.Resource
import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.domain.repository.CompetitionsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCompetitionDetailsFromDBUseCase @Inject constructor(
    private val repo: CompetitionsRepo
) {


    operator fun invoke(code:String):Flow<Resource<CompetitionDetailsResponse>> = flow{
        emit(Resource.Loading<CompetitionDetailsResponse>())
        try {
            val competitionDetails = repo.getCompetitionDetailsFromDb(code)
            emit(Resource.Success<CompetitionDetailsResponse>(competitionDetails))
            println("CompetitionDetailsFromDB Repo ${competitionDetails.toString()}")


        }catch (e:Exception){
            emit(Resource.Error<CompetitionDetailsResponse>(e.cause?.message.toString()))
            println("CompetitionDetailsFromDB Repo ${e.localizedMessage}")
        }


    }
}