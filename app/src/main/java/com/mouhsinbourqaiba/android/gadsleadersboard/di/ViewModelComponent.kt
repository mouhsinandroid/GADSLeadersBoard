package com.mouhsinbourqaiba.android.gadsleadersboard.di

import com.mouhsinbourqaiba.android.gadsleadersboard.view.home.learner.ListLearnersViewModel
import com.mouhsinbourqaiba.android.gadsleadersboard.view.home.skilliq.ListSkillLeadersViewModel
import com.mouhsinbourqaiba.android.gadsleadersboard.view.submitproject.SubmitProjectViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class])
interface ViewModelComponent {
    fun injectViewLearnerServiceApi(viewModel: ListLearnersViewModel)

    fun injectViewSkillLeadersServiceApi(viewModel: ListSkillLeadersViewModel)

    fun injectViewSubmitFormServiceApi(viewModel: SubmitProjectViewModel)


}