package com.mouhsinbourqaiba.android.gadsleadersboard.model

import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerApiLearnerComponent
import io.reactivex.Single
import javax.inject.Inject

class ApiServices {

    @Inject
    lateinit var api: Apis

    init {
        DaggerApiLearnerComponent.create().injectApi(this)
    }

    fun getLearners(): Single<List<LearningLeader>> {
        return api.getLearners()
    }
}