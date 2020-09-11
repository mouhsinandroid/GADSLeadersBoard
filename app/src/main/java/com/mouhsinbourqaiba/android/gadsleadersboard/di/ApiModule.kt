package com.mouhsinbourqaiba.android.gadsleadersboard.di

import com.google.gson.GsonBuilder
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import com.mouhsinbourqaiba.android.gadsleadersboard.model.Apis
import com.mouhsinbourqaiba.android.gadsleadersboard.model.SubmitFormApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
open class ApiModule {
    private val BASE_URL = "https://gadsapi.herokuapp.com"

    private val SUBMIT_FORM_BASE_URL = "https://docs.google.com/forms/d/e/"

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
    fun provideSubmitFormApi(): SubmitFormApi {
        return  Retrofit.Builder()
            .baseUrl(SUBMIT_FORM_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(SubmitFormApi::class.java)
    }

    @Provides
    open fun provideApiService(): ApiServices {
        return ApiServices()
    }


}