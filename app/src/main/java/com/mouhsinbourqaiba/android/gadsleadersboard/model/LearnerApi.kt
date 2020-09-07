package com.mouhsinbourqaiba.android.gadsleadersboard.model

import io.reactivex.Single
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface LearnerApi {

    @GET("/api/hours")
    fun getLearners(): Single<List<LearningLeader>>
}