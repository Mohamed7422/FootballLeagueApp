package com.example.footballleagueapp.domain.usecases

import com.example.footballleagueapp.common.Resource
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.domain.repository.CompetitionsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCompetitionsFromDBUseCase @Inject constructor(
    private val repo: CompetitionsRepo
) {

    operator fun invoke(): Flow<Resource<List<Competition>>> = flow {
        emit(Resource.Loading<List<Competition>>())
        try {

            val competitionsResponse = repo.getCompetitionsList()
            emit(Resource.Success<List<Competition>>(competitionsResponse))

        } catch (e: Exception) {
            emit(
                Resource.Error<List<Competition>>(
                    e.localizedMessage ?: "Exception in database "
                )
            )
        }


    }
}