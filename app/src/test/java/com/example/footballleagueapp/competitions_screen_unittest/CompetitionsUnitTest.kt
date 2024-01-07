package com.example.footballleagueapp.competitions_screen_unittest

import com.example.footballleagueapp.FakeData
import com.example.footballleagueapp.MainCoroutineRule
import com.example.footballleagueapp.data.localdb.localcompetition.CompetitionDao
import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.data.model.competitions_list_model.CompetitionsResponse
import com.example.footballleagueapp.data.model.competitions_list_model.Filters
import com.example.footballleagueapp.data.remotedb.CompetitionsApiService
import com.example.footballleagueapp.data.remotedb.CompetitionsRepoImpl
import com.example.footballleagueapp.domain.usecases.GetCompetitionsFromDBUseCase
import com.example.footballleagueapp.domain.usecases.GetCompetitionsUseCase
import com.example.footballleagueapp.ui.competitions_screen_components.CompetitionsState
import com.example.footballleagueapp.ui.competitions_screen_components.CompetitionsViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CompetitionsUnitTest {

    /*
    * In case you check the error state , kindly replace the api client and dao
    * with the TestCompetitionsErrorStateApiClient and TestCompetitionsErrorStateDao
    * and do so for the rest of states.
    *
    * */

    val competitionsList = mutableListOf<Competition>(FakeData.fakeCompetition)

    @get:Rule
    var mainCoroutineScope = MainCoroutineRule()

    private val testScope = TestScope(mainCoroutineScope.testScheduler)


    @Test
   fun fetchDataState_isSetCorrectly() = testScope.runTest {
       val viewModel = getViewModel()

       val competitionsState = viewModel.competitionsState.value
       assertTrue(competitionsState == CompetitionsState(
           isLoading = false,
           competitions = competitionsList,
           error = ""
       ))
   }

    @Test
   fun fetchDataErrorState_isSetCorrectly() = testScope.runTest {
       val viewModel = getViewModel()

       val competitionsState = viewModel.competitionsState.value
       assertTrue(competitionsState == CompetitionsState(
           isLoading = false,
           competitions = emptyList(),
           error =competitionsState.error
       ))
   }

    @Test
   fun fetchDataLoadingState_isSetCorrectly() = testScope.runTest {
       val viewModel = getViewModel()

       val competitionsState = viewModel.competitionsState.value
       assertTrue(competitionsState == CompetitionsState(
           isLoading = true,
           competitions = emptyList(),
           error =""
       ))
   }



    private fun getViewModel(): CompetitionsViewModel {
        val repo = CompetitionsRepoImpl(TestCompetitionsLoadingStateDao(),TestCompetitionsLoadingStateApiClient())
        val getCompetitionsFromDBUseCase = GetCompetitionsFromDBUseCase(repo)
        val getCompetitionsUseCase = GetCompetitionsUseCase(repo)
        return CompetitionsViewModel(getCompetitionsUseCase,getCompetitionsFromDBUseCase)
    }


    class TestCompetitionsApiClient():CompetitionsApiService{
        override suspend fun getCompetitions(): CompetitionsResponse {
            val competitionsList = mutableListOf<Competition>(FakeData.fakeCompetition)
            return CompetitionsResponse(
                competitions = competitionsList, 20,
                Filters()
            )
        }

        override suspend fun getCompetitionDetails(code: String): CompetitionDetailsResponse {
            TODO("Not yet implemented")
        }

    }


    class TestCompetitionsDao:CompetitionDao{
        override suspend fun insertCompetitionInDb(competition: Competition) {
            TODO("Not yet implemented")
        }

        override suspend fun insertCompetitions(competitions: List<Competition>) {
            TODO("Not yet implemented")
        }

        override suspend fun getCompetitionsList(): List<Competition> {
            val competitionsList = mutableListOf<Competition>(FakeData.fakeCompetition)
            return  competitionsList
        }

        override suspend fun getCompetition(id: Int?): Competition {
            return FakeData.fakeCompetition
        }

        override suspend fun updateCompetitionsList(competitionsList: List<Competition>) {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCompetitionsList() {
            TODO("Not yet implemented")
        }

        override suspend fun insertCompetitionDetailsInDb(competitionDetails: CompetitionDetailsResponse) {
            TODO("Not yet implemented")
        }

        override suspend fun getCompetitionDetailsFromDb(code: String): CompetitionDetailsResponse {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCompetitionDetails() {
            TODO("Not yet implemented")
        }


    }


    class TestCompetitionsErrorStateApiClient():CompetitionsApiService{
        override suspend fun getCompetitions(): CompetitionsResponse {
            val competitionsList = emptyList<Competition>()
            return CompetitionsResponse(
                competitions = competitionsList, 20,
                Filters()
            )
        }

        override suspend fun getCompetitionDetails(code: String): CompetitionDetailsResponse {
            TODO("Not yet implemented")
        }

    }


    class TestCompetitionsErrorStateDao:CompetitionDao{
        private val repoStorage = mutableListOf<Competition>()
        override suspend fun insertCompetitionInDb(competition: Competition) {

        }

        override suspend fun insertCompetitions(competitions: List<Competition>) {
            competitions.forEach{
                repoStorage.add(it.id,it)
            }
        }

        override suspend fun getCompetitionsList(): List<Competition> {
            return emptyList()
        }

        override suspend fun getCompetition(id: Int?): Competition {
            return FakeData.fakeCompetition
        }

        override suspend fun updateCompetitionsList(competitionsList: List<Competition>) {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCompetitionsList() {
            TODO("Not yet implemented")
        }

        override suspend fun insertCompetitionDetailsInDb(competitionDetails: CompetitionDetailsResponse) {
            TODO("Not yet implemented")
        }

        override suspend fun getCompetitionDetailsFromDb(code: String): CompetitionDetailsResponse {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCompetitionDetails() {
            TODO("Not yet implemented")
        }


    }

    class TestCompetitionsLoadingStateApiClient():CompetitionsApiService{
        override suspend fun getCompetitions(): CompetitionsResponse {
            val competitionsList = emptyList<Competition>()

            delay(5000)
            return CompetitionsResponse(
                competitions = competitionsList, 20,
                Filters()
            )
        }

        override suspend fun getCompetitionDetails(code: String): CompetitionDetailsResponse {
            TODO("Not yet implemented")
        }

    }


    class TestCompetitionsLoadingStateDao:CompetitionDao{
        private val repoStorage = mutableListOf<Competition>()
        override suspend fun insertCompetitionInDb(competition: Competition) {

        }

        override suspend fun insertCompetitions(competitions: List<Competition>) {
            competitions.forEach{
                repoStorage.add(it.id,it)
            }
        }

        override suspend fun getCompetitionsList(): List<Competition> {
            return emptyList()
        }

        override suspend fun getCompetition(id: Int?): Competition {
            return FakeData.fakeCompetition
        }

        override suspend fun updateCompetitionsList(competitionsList: List<Competition>) {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCompetitionsList() {
            TODO("Not yet implemented")
        }

        override suspend fun insertCompetitionDetailsInDb(competitionDetails: CompetitionDetailsResponse) {
            TODO("Not yet implemented")
        }

        override suspend fun getCompetitionDetailsFromDb(code: String): CompetitionDetailsResponse {
            TODO("Not yet implemented")
        }

        override suspend fun deleteCompetitionDetails() {
            TODO("Not yet implemented")
        }


    }

}