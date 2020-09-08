package com.mouhsinbourqaiba.android.gadsleadersboard.model

import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class ApiServices {

    @Inject
    lateinit var api: Apis

    init {
        DaggerApiComponent.create().injectApi(this)
    }

    fun getLearners(): Single<List<LearningLeader>> {
        return api.getLearners()
    }

    fun getSkillLeaders(): Single<List<SkillIqLeader>> {
        return api.getSkillLeaders()
    }
}