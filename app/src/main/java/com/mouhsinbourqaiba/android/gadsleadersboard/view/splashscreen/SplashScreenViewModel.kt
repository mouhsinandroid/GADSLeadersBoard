package com.mouhsinbourqaiba.android.gadsleadersboard.view.splashscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel: ViewModel() {

    val liveData : LiveData<SplashState>
        get() = mutableLiveData

    private val mutableLiveData = MutableLiveData<SplashState>()
    init {
        GlobalScope.launch {
            delay(2000)
            mutableLiveData.postValue(SplashState.MainActivity)
        }
    }
}

sealed class SplashState {
    object MainActivity : SplashState()
}
