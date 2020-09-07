package com.mouhsinbourqaiba.android.gadsleadersboard.di

import com.mouhsinbourqaiba.android.gadsleadersboard.view.home.learner.ListLearnersViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface ViewModelComponent {
    fun injectViewLearnerServiceApi(viewModel: ListLearnersViewModel)
}