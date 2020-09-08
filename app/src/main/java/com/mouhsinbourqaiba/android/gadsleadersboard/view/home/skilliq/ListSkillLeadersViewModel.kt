package com.mouhsinbourqaiba.android.gadsleadersboard.view.home.skilliq

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mouhsinbourqaiba.android.gadsleadersboard.di.AppModule
import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerViewModelComponent
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import com.mouhsinbourqaiba.android.gadsleadersboard.model.SkillIqLeader
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListSkillLeadersViewModel(application: Application): AndroidViewModel(application) {

    constructor(application: Application, test: Boolean= true): this(application) {
        injected = true
    }

    val skillLeaders by lazy { MutableLiveData<List<SkillIqLeader>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var apiServices: ApiServices

    private val disposable = CompositeDisposable()

    private var injected = false

    fun inject() {
        if(!injected) {
            DaggerViewModelComponent.builder().appModule(AppModule(getApplication()))
                .build().injectViewSkillLeadersServiceApi(this)
        }
    }

    fun refreshDataSkillLeaders() {
        inject()
        loading.value  = true
        getSkillLeaders()
    }


    private fun getSkillLeaders() {

        disposable.add(
            apiServices.getSkillLeaders()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object : DisposableSingleObserver<List<SkillIqLeader>>(){
                    override fun onSuccess(listSkillLearners: List<SkillIqLeader>) {
                        loadError.value = false
                        skillLeaders.value = listSkillLearners
                        loading.value = false

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        skillLeaders.value = null
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