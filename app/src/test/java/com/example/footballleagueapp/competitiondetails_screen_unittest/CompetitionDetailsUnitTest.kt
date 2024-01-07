package com.example.footballleagueapp.competitiondetails_screen_unittest

import androidx.lifecycle.SavedStateHandle
import com.example.footballleagueapp.FakeData
import com.example.footballleagueapp.MainCoroutineRule
import com.example.footballleagueapp.data.localdb.localcompetition.CompetitionDao
import com.example.footballleagueapp.data.model.competition_info_model.CompetitionDetailsResponse
import com.example.footballleagueapp.data.model.competitions_list_model.Competition
import com.example.footballleagueapp.data.model.competitions_list_model.CompetitionsResponse
import com.example.footballleagueapp.data.model.competitions_list_model.Filters
import com.example.footballleagueapp.data.remotedb.CompetitionsApiService
import com.example.footballleagueapp.data.remotedb.CompetitionsRepoImpl
import com.example.footballleagueapp.domain.usecases.GetCompetitionDetailsFromDBUseCase
import com.example.footballleagueapp.domain.usecases.GetCompetitionDetailsUseCase
import com.example.footballleagueapp.ui.competition_details_screen_components.CompetitionDetailsState
import com.example.footballleagueapp.ui.competition_details_screen_components.components.DetailsViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CompetitionDetailsUnitTest {

    /*
    * In case you check the error state , kindly replace the api client and dao
    * with the TestCompetitionsErrorStateApiClient and TestCompetitionsErrorStateDao
    * and do so for the rest of states.
    *
    * */

   val competitionDetail = FakeData.fakeCompetitionDetailsResponse

    @get:Rule
    var mainCoroutineScope = MainCoroutineRule()

    private val testScope = TestScope(mainCoroutineScope.testScheduler)


    @Test
   fun fetchDataState_isSetCorrectly() = testScope.runTest {
       val viewModel = getViewModel()
        viewModel.getCompetitionDetailsFromDB(""){
            hasdata -> false
        }
        //This is to get from remote source But if you need to get from database then remove this line
        //and replace false to true in the above line ->  "hasdata -> true"

        viewModel.getCompetitionDetails("")

        val competitionDetailsState = viewModel.competitionDetailsState.value
        assertTrue(competitionDetailsState == CompetitionDetailsState(
            isLoading = false,
            competitionDetails = competitionDetail,
            error = ""
        ))

    }

    @Test
   fun fetchDataErrorState_isSetCorrectly() = testScope.runTest {
       val viewModel = getViewModel()
       val competitionDetailsState = viewModel.competitionDetailsState.value
       assertTrue(competitionDetailsState == CompetitionDetailsState(
           isLoading = false,
           competitionDetails = null,
           error = competitionDetailsState.error

       ))


   }

    @Test
   fun fetchDataLoadingState_isSetCorrectly() = testScope.runTest {
        //In case of loading , kindly change the dao and apiClient in repository
        //to TestLoadingState for each one.

       val viewModel = getViewModel()
        viewModel.getCompetitionDetailsFromDB(""){
            hasData->true
        }
       val competitionDetailsState = viewModel.competitionDetailsState.value
       assertTrue(competitionDetailsState == CompetitionDetailsState(
           isLoading = true,
           competitionDetails = null,
           error = ""

       ))

   }



    private fun getViewModel(): DetailsViewModel {
        val savedStateHandle = SavedStateHandle()
        val repo = CompetitionsRepoImpl(TestCompetitionsDao(),TestCompetitionsApiClient())
        val getCompetitionDetailsFromDBUseCase = GetCompetitionDetailsUseCase(repo)
        val getCompetitionDetailsUseCase = GetCompetitionDetailsFromDBUseCase(repo)
        return DetailsViewModel(getCompetitionDetailsUseCase,getCompetitionDetailsFromDBUseCase,savedStateHandle)
    }


    class TestCompetitionsApiClient():CompetitionsApiService{
        override suspend fun getCompetitions(): CompetitionsResponse {
            TODO()
        }

        override suspend fun getCompetitionDetails(code: String): CompetitionDetailsResponse {
           return FakeData.fakeCompetitionDetailsResponse
        }

    }


    class TestCompetitionsDao:CompetitionDao{
        private val repoStorage = mutableListOf<CompetitionDetailsResponse>()

        override suspend fun insertCompetitionInDb(competition: Competition) {
           TODO()
        }

        override suspend fun insertCompetitions(competitions: List<Competition>) {
            TODO("Not yet implemented")
        }

        override suspend fun getCompetitionsList(): List<Competition> {
            TODO()
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
            repoStorage.add(competitionDetails)
        }

        override suspend fun getCompetitionDetailsFromDb(code: String): CompetitionDetailsResponse {
            return FakeData.fakeCompetitionDetailsResponse
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
            delay(6000)
            return FakeData.fakeCompetitionDetailsResponse
        }

    }


    class TestCompetitionsLoadingStateDao:CompetitionDao{

        private val repoDbStorage = mutableListOf<CompetitionDetailsResponse>()
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
            delay(5000)
            repoDbStorage.add(competitionDetails)
        }

        override suspend fun getCompetitionDetailsFromDb(code: String): CompetitionDetailsResponse {
            delay(6000)
            return FakeData.fakeCompetitionDetailsResponse
        }

        override suspend fun deleteCompetitionDetails() {
            TODO("Not yet implemented")
        }


    }

}