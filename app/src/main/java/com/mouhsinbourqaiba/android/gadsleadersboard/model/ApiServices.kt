package com.mouhsinbourqaiba.android.gadsleadersboard.model

import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerApiComponent
import io.reactivex.Single
import retrofit2.Call
import javax.inject.Inject

class ApiServices {

    @Inject
    lateinit var api: Apis

    @Inject
    lateinit var submitFormApi: SubmitFormApi

    init {
        DaggerApiComponent.create().injectApi(this)
    }

    fun getLearners(): Single<List<LearningLeader>> {
        return api.getLearners()
    }

    fun getSkillLeaders(): Single<List<SkillIqLeader>> {
        return api.getSkillLeaders()
    }

    fun executeSubmitForm(emailAddress: String, firstName: String, lastName: String, projectLink: String): Call<Void> {
        return submitFormApi.submissionFormAsync(emailAddress, firstName, lastName, projectLink)
    }
}