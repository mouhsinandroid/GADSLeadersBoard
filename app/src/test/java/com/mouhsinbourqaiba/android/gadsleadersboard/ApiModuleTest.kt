package com.mouhsinbourqaiba.android.gadsleadersboard

import com.mouhsinbourqaiba.android.gadsleadersboard.di.ApiModule
import com.mouhsinbourqaiba.android.gadsleadersboard.model.LearnerApiService

class ApiModuleTest(val mockService: LearnerApiService): ApiModule() {

    override fun provideLearnerApiService(): LearnerApiService {
        return mockService
    }
}