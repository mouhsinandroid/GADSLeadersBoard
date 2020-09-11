package com.mouhsinbourqaiba.android.gadsleadersboard.view.submitproject

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.mouhsinbourqaiba.android.gadsleadersboard.di.AppModule
import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerViewModelComponent
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import javax.inject.Inject


class SubmitProjectViewModel(application: Application): AndroidViewModel(application) {

    val successStatus by lazy { MutableLiveData<Boolean>() }
    val errorStatus by lazy { MutableLiveData<Boolean>() }
    val loadingStatus by lazy { MutableLiveData<Boolean>() }

    @Inject
    lateinit var apiServices: ApiServices

    private val disposable = CompositeDisposable()

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


//        disposable.add(
//            apiServices.executeSubmitForm(emailAddress, firstName, lastName, projectLink)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(
//                    { result -> Log.v("POSTED FORM", "" + result) },
//                    { error -> Log.e("ERROR", error.message + error.stackTrace) }
//                )
//        )


        apiServices.executeSubmitForm(emailAddress, firstName, lastName, projectLink).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                    { result -> Log.v("POSTED FORM", "" + result) },
                    { error -> Log.e("ERROR", error.message + error.stackTrace) }
                )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}


