package com.mouhsinbourqaiba.android.gadsleadersboard.view.home.learner

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mouhsinbourqaiba.android.gadsleadersboard.di.AppModule
import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerViewModelComponent
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import com.mouhsinbourqaiba.android.gadsleadersboard.model.LearningLeader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListLearnersViewModel(application: Application): AndroidViewModel(application) {

    constructor(application: Application, test: Boolean= true): this(application) {
        injected = true
    }

    val learners by lazy { MutableLiveData<List<LearningLeader>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var apiServices: ApiServices

    private val disposable = CompositeDisposable()

    private var injected = false


    fun inject() {
        if(!injected) {
            DaggerViewModelComponent.builder().appModule(AppModule(getApplication()))
                .build().injectViewLearnerServiceApi(this)
        }
    }

    fun refreshDataLearners() {
        inject()
        loading.value  = true
        getLearners()
    }


    private fun getLearners() {

        disposable.add(
            apiServices.getLearners()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<List<LearningLeader>>(){
                    override fun onSuccess(listLearners: List<LearningLeader>) {
                        loadError.value = false
                        learners.value = listLearners
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        learners.value = null
                        loadError.value = true
                    }

                })
        )

    }
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}