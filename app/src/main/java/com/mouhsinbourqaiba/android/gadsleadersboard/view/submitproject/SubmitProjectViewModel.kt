package com.mouhsinbourqaiba.android.gadsleadersboard.view.submitproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mouhsinbourqaiba.android.gadsleadersboard.di.AppModule
import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerViewModelComponent
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class SubmitProjectViewModel(application: Application): AndroidViewModel(application) {

    val successStatus by lazy { MutableLiveData<Boolean>() }
    val errorStatus by lazy { MutableLiveData<Boolean>() }
    val loadingStatus by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var apiServices: ApiServices

    init {
        DaggerViewModelComponent.builder().appModule(AppModule(getApplication()))
            .build().injectViewSubmitFormServiceApi(this)
    }

    fun postFormProject(
        emailAddress: String,
        firstName: String,
        lastName: String,
        projectLink: String
    ) {
        loadingStatus.value = true
        apiServices.executeSubmitForm(emailAddress, firstName, lastName, projectLink).enqueue(object:
            Callback<Void> {

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Submission Failure: ${t.message}")
                errorStatus.value = true
                successStatus.value = false
                loadingStatus.value = false
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {

                if(response.isSuccessful){
                    println("Submission isSuccessful: ${response.body()}")
                    errorStatus.value = false
                    successStatus.value = true
                    loadingStatus.value = false

                }else{
                    println("Submission Fail: ${response.body()}")
                    errorStatus.value = true
                    successStatus.value = false
                    loadingStatus.value = false
                }

            }
        })
    }

    override fun onCleared() {
        super.onCleared()
    }

}


