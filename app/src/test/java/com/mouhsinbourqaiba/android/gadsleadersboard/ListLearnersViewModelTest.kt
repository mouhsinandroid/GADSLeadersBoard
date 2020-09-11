package com.mouhsinbourqaiba.android.gadsleadersboard

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mouhsinbourqaiba.android.gadsleadersboard.di.AppModule
import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerViewModelComponent
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import com.mouhsinbourqaiba.android.gadsleadersboard.model.LearningLeader
import com.mouhsinbourqaiba.android.gadsleadersboard.view.home.learner.ListLearnersViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ListLearnersViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var services: ApiServices

    private val application = Mockito.mock(Application::class.java)

    private var viewModel = ListLearnersViewModel(application, true)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder().appModule(AppModule(application))
            .apiModule(ApiModuleTest(services))
            .build()
            .injectViewLearnerServiceApi(viewModel)
    }

    @Test
    fun getLearnerSuccess() {

        val learner = LearningLeader("Mouhsin", 300, "Morocco", null)
        val listLearners = listOf(learner)

        val testSingle = Single.just(listLearners)

        Mockito.`when`(services.getLearners()).thenReturn(testSingle)

        viewModel.refreshDataLearners()

        Assert.assertEquals(1, viewModel.learners.value?.size)
        Assert.assertEquals(false, viewModel.loading.value)
        Assert.assertEquals(false, viewModel.loadError.value)

    }

    @Test
    fun getLearnersFailure() {

        val testSingle = Single.error<List<LearningLeader>>(Throwable())

        Mockito.`when`(services.getLearners()).thenReturn(testSingle)

        viewModel.refreshDataLearners()

        Assert.assertEquals(null, viewModel.learners.value?.size)
        Assert.assertEquals(false, viewModel.loading.value)
        Assert.assertEquals(true, viewModel.loadError.value)


    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker({ it.run()}, true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }

}