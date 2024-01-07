package com.example.footballleagueapp.ui.competition_details_screen_components.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballleagueapp.common.Constants
import com.example.footballleagueapp.common.Resource
import com.example.footballleagueapp.domain.usecases.GetCompetitionDetailsFromDBUseCase
import com.example.footballleagueapp.domain.usecases.GetCompetitionDetailsUseCase
import com.example.footballleagueapp.ui.competition_details_screen_components.CompetitionDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val databaseUseCase: GetCompetitionDetailsFromDBUseCase,
    private val networkUseCase: GetCompetitionDetailsUseCase,
    savedStateHandle: SavedStateHandle
):ViewModel() {

    private var competitionCode: String =""
    private val _competitionDetailsState = mutableStateOf(CompetitionDetailsState())
    val competitionDetailsState :State<CompetitionDetailsState> = _competitionDetailsState

init {
    savedStateHandle.get<String>(Constants.PARAM_COMPETITION_CODE)?.let {competitionCode->
        this.competitionCode = competitionCode
        println("Code of comp $competitionCode")
        getCompetitionDetailsFromDB(competitionCode){hasData ->
            if (!hasData){
                getCompetitionDetails(competitionCode)
            }

        }

    }
}


      fun getCompetitionDetails(competitionCode:String){
        networkUseCase(competitionCode).onEach { result ->

        when(result){
            is Resource.Success ->{
                 _competitionDetailsState.value =
                     CompetitionDetailsState(competitionDetails = result.data)
                println("Getting data from netuork")
            }

            is Resource.Error -> {
                 _competitionDetailsState.value = CompetitionDetailsState(error = result.message?:
                            "Unexpected error")
            }

            is Resource.Loading -> {
                 _competitionDetailsState.value = CompetitionDetailsState(isLoading = true)
            }
        }

        }.launchIn(viewModelScope)
    }

      fun getCompetitionDetailsFromDB(competitionCode: String,onResult:(Boolean)->Unit){
        databaseUseCase(competitionCode).onEach { result->
            println("getCompetitionDetailsFromDB From ${result.message}")
            when(result){

                is Resource.Success -> {
                    _competitionDetailsState.value = CompetitionDetailsState(competitionDetails =
                    result.data)
                    println("Getting data from local")
                    if (result.data==null){
                        onResult(false)
                    }
                    onResult(true)


                }

                is Resource.Error -> {
                    _competitionDetailsState.value = CompetitionDetailsState(error = result.message?:
                    "Unexpected error")
                    onResult(false)
                }
                is Resource.Loading -> {
                    _competitionDetailsState.value = CompetitionDetailsState(isLoading = true)
                }
            }

        }.launchIn(viewModelScope)
    }

    fun loadData() {

            getCompetitionDetailsFromDB(competitionCode){hasData ->
                if (!hasData){
                    getCompetitionDetails(competitionCode)
                }

            }

    }

}