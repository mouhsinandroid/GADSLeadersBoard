package com.mouhsinbourqaiba.android.gadsleadersboard

import com.mouhsinbourqaiba.android.gadsleadersboard.di.ApiModule
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices

class ApiModuleTest(private val mockServices: ApiServices): ApiModule() {

    override fun provideApiService(): ApiServices {
        return mockServices
    }
}