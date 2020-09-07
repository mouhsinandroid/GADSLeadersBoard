package com.mouhsinbourqaiba.android.gadsleadersboard.di

import com.mouhsinbourqaiba.android.gadsleadersboard.model.LearnerApi
import com.mouhsinbourqaiba.android.gadsleadersboard.model.LearnerApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://gadsapi.herokuapp.com"

    @Provides
    fun provideLearnerApi(): LearnerApi {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(LearnerApi::class.java)
    }

    @Provides
    open fun provideLearnerApiService(): LearnerApiService {
        return LearnerApiService()
    }


}