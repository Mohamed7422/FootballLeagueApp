package com.example.footballleagueapp.domain.usecases

import com.example.footballleagueapp.common.Resource
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.domain.repository.CompetitionsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCompetitionsUseCase @Inject constructor(
    private val repo: CompetitionsRepo
) {

    operator fun invoke(): Flow<Resource<List<Competition>>> = flow {

        try {
            emit(Resource.Loading<List<Competition>>())
            val competitionsResponse = repo.getCompetitions()
            emit(Resource.Success<List<Competition>>(competitionsResponse.competitions))
             repo.insertCompetitionsInDb(competitionsResponse.competitions)

        } catch (e: HttpException) {
            emit(
                Resource.Error<List<Competition>>(
                    e.localizedMessage ?: "Unexpected error"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error<List<Competition>>(
                    e.localizedMessage ?: "internetConnection "
                )
            )
        }


    }
}