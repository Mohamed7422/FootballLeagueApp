package com.example.footballleagueapp.domain.usecases

import com.example.footballleagueapp.common.Resource
import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.domain.repository.CompetitionsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCompetitionDetailsUseCase @Inject constructor(
    private val repo: CompetitionsRepo
) {


    operator fun invoke(code:String):Flow<Resource<CompetitionDetailsResponse>> = flow{
        emit(Resource.Loading<CompetitionDetailsResponse>())
        try {
            val competitionDetails = repo.getCompetitionDetails(code)
            emit(Resource.Success<CompetitionDetailsResponse>(competitionDetails))
            println("CompetitionDetailsFrom Repo ${competitionDetails.toString()}")
            repo.insertCompetitionDetailsInDb(competitionDetails)


        }catch (e:HttpException){
            emit(Resource.Error<CompetitionDetailsResponse>(e.localizedMessage?:"Unexpected Error"))
            println("CompetitionDetailsFrom Repo ${e.localizedMessage}")

        }catch (e:IOException){
            emit(Resource.Error<CompetitionDetailsResponse>("Check internetConnection."))
            println("CompetitionDetailsFrom Repo ${e.localizedMessage}")
        }


    }
}