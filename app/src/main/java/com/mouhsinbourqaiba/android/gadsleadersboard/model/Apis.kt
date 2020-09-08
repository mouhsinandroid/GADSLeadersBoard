package com.mouhsinbourqaiba.android.gadsleadersboard.model

import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface Apis {

    @GET("/api/hours")
    fun getLearners(): Single<List<LearningLeader>>

    @GET("/api/skilliq")
    fun getSkillLeaders(): Single<List<SkillIqLeader>>
}