package com.mouhsinbourqaiba.android.gadsleadersboard.di

import com.mouhsinbourqaiba.android.gadsleadersboard.model.Apis
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
open class ApiModule {
    private val BASE_URL = "https://gadsapi.herokuapp.com"

    @Provides
    fun provideLearnerApi(): Apis {
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(Apis::class.java)
    }

    @Provides
    open fun provideLearnerApiService(): ApiServices {
        return ApiServices()
    }


}