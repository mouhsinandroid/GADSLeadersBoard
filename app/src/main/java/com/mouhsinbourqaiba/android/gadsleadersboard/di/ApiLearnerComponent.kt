package com.mouhsinbourqaiba.android.gadsleadersboard.di

import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiLearnerComponent {

    fun injectApi(services: ApiServices)
}