package com.example.footballleagueapp.di

import android.content.Context
import androidx.room.Room
import com.example.footballleagueapp.common.Constants
import com.example.footballleagueapp.data.localdb.localcompetition.CompetitionDao
import com.example.footballleagueapp.data.localdb.localcompetition.CompetitionsDataBase
import com.example.footballleagueapp.data.remotedb.CompetitionsApiService
import com.example.footballleagueapp.data.remotedb.CompetitionsRepoImpl
import com.example.footballleagueapp.domain.repository.CompetitionsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiInterceptor(it: Interceptor.Chain): Response {

            val originalRequest = it.request()


            val newRequest = originalRequest.newBuilder()
                .addHeader("X-Auth-Token",Constants.API_KEY)
                .build()

        return it.proceed(newRequest)


    }

    @Provides
    @Singleton
     fun provideOkHttpClient():OkHttpClient{

        val certificatePinner  =  CertificatePinner.Builder()
            .add("api.football-data.org","sha256/${Constants.SHA_KEY}")
            .build()


        return OkHttpClient.Builder()
            .certificatePinner(certificatePinner)
             .addInterceptor {
                 chain ->  provideApiInterceptor(chain)
             }.build()
     }

    @Provides
    @Singleton
     fun providesCompetitionsApiClient(): CompetitionsApiService {
         return Retrofit.Builder()
             .client(provideOkHttpClient())
             .baseUrl(Constants.BASE_URL)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(CompetitionsApiService::class.java)
     }


    @Provides
    @Singleton
    fun providesCompetitionsDataBase(@ApplicationContext context:Context):CompetitionsDataBase{
        return Room.databaseBuilder(context,
            CompetitionsDataBase::class.java,"competitionsDataBase.dp").build()
    }

    @Provides
    fun providesCompetitionsDao(competitionsDataBase: CompetitionsDataBase):CompetitionDao{
        return competitionsDataBase.competitionsDao()
    }




    @Provides
    @Singleton
    fun providesCompetitionsRepo(dao:CompetitionDao,api: CompetitionsApiService):CompetitionsRepo{
        return CompetitionsRepoImpl(dao,api)
    }





}