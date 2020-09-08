package com.mouhsinbourqaiba.android.gadsleadersboard

import com.mouhsinbourqaiba.android.gadsleadersboard.di.ApiModule
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices

class ApiModuleTest(val mockServices: ApiServices): ApiModule() {

    override fun provideLearnerApiService(): ApiServices {
        return mockServices
    }
}