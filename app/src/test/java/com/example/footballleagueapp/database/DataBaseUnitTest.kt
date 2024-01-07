package com.example.footballleagueapp.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.example.footballleagueapp.FakeData
import com.example.footballleagueapp.data.localdb.localcompetition.CompetitionDao
import com.example.footballleagueapp.data.localdb.localcompetition.CompetitionsDataBase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class DataBaseUnitTest {

    private var testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private lateinit var database: CompetitionsDataBase
    private lateinit var dao: CompetitionDao

    @Before
    fun createDb() {
        // Use an in-memory database for testing.
        database = Room.inMemoryDatabaseBuilder(
          ApplicationProvider.getApplicationContext()  , CompetitionsDataBase::class.java)
            .build()
        dao = database.competitionsDao()
    }


    @After
    fun tearUp(){
        database.close()
    }
   @Test
    fun addToDataBase() = testScope.runTest{
       dao.insertCompetitionInDb(FakeData.fakeCompetition)

    }
     @Test
    fun getFromDataBase() = testScope.runTest{
         val fakeData = FakeData.fakeCompetition
         dao.insertCompetitionInDb(fakeData)
       val competition = dao.getCompetition(fakeData.id)
         assertEquals(fakeData,competition)

    }


}