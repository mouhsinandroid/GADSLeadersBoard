package com.mouhsinbourqaiba.android.gadsleadersboard.di

import com.mouhsinbourqaiba.android.gadsleadersboard.model.LearnerApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiLearnerComponent {

    fun injectApi(service: LearnerApiService)
}