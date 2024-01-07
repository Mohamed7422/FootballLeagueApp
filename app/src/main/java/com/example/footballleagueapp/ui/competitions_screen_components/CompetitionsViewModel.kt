package com.example.footballleagueapp.ui.competitions_screen_components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballleagueapp.common.Resource
import com.example.footballleagueapp.domain.usecases.GetCompetitionsFromDBUseCase
import com.example.footballleagueapp.domain.usecases.GetCompetitionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CompetitionsViewModel @Inject constructor(
    private val getCompetitionsUseCase: GetCompetitionsUseCase,
    private val getCompetitionsFromDBUseCase: GetCompetitionsFromDBUseCase
) : ViewModel() {

    private val _competitionsState = mutableStateOf(CompetitionsState())
    val competitionsState: State<CompetitionsState> = _competitionsState


    init {
           getCompetitionsFromDataBase {hasData ->
           if (!hasData){
               getCompetitions()
            }

           }



    }


      fun getCompetitions() {
        getCompetitionsUseCase().onEach { result ->
            when (result) {

                is Resource.Success ->
                    _competitionsState.value = CompetitionsState(competitions = result.data?: emptyList())

                is Resource.Error ->
                    _competitionsState.value = CompetitionsState(error = result.message?:"UnExpected Error")

                is Resource.Loading ->
                    _competitionsState.value = CompetitionsState(isLoading = true)
            }

        }.launchIn(viewModelScope)
    }

      fun getCompetitionsFromDataBase(onResult:(Boolean) ->Unit){
        getCompetitionsFromDBUseCase().onEach {result ->
            println("getCompetitionsFromDataBaseComp ${result.data}")
            when (result) {

                is Resource.Success ->{
                    _competitionsState.value = CompetitionsState(competitions = result.data?: emptyList())
                   if (result.data?.isEmpty() == true){
                       onResult(false)
                   }
                    onResult(true)
                }


                is Resource.Error ->{
                    _competitionsState.value = CompetitionsState(error = result.message?:"UnExpected Error")
                    onResult(false)
                }


                is Resource.Loading ->
                    _competitionsState.value = CompetitionsState(isLoading = true)
            }

        }.launchIn(viewModelScope)


    }

    fun loadData() {
        getCompetitionsFromDataBase {hasData ->
            if (!hasData){
                getCompetitions()
            }

        }
    }


}