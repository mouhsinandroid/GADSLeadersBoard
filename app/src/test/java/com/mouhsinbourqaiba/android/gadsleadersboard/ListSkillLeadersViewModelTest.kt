package com.mouhsinbourqaiba.android.gadsleadersboard

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mouhsinbourqaiba.android.gadsleadersboard.di.AppModule
import com.mouhsinbourqaiba.android.gadsleadersboard.di.DaggerViewModelComponent
import com.mouhsinbourqaiba.android.gadsleadersboard.model.ApiServices
import com.mouhsinbourqaiba.android.gadsleadersboard.model.SkillIqLeader
import com.mouhsinbourqaiba.android.gadsleadersboard.view.home.skilliq.ListSkillLeadersViewModel
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
import java.util.concurrent.Executor

class ListSkillLeadersViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    lateinit var services: ApiServices

    val application = Mockito.mock(Application::class.java)

    var viewModel = ListSkillLeadersViewModel(application, true)


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        DaggerViewModelComponent.builder().appModule(AppModule(application))
            .apiModule(ApiModuleTest(services))
            .build()
            .injectViewSkillLeadersServiceApi(viewModel)
    }

    @Test
    fun getSkillLeadersSuccess() {

        val skillLeader = SkillIqLeader("Mouhsin", 300, "Morocco", null)
        val listSkillLeaders = listOf(skillLeader)

        val testSingle = Single.just(listSkillLeaders)

        Mockito.`when`(services.getSkillLeaders()).thenReturn(testSingle)

        viewModel.refreshDataSkillLeaders()

        Assert.assertEquals(1, viewModel.skillLeaders.value?.size)
        Assert.assertEquals(false, viewModel.loading.value)
        Assert.assertEquals(false, viewModel.loadError.value)

    }

    @Test
    fun getSkillLeadersFailure() {

        val testSingle = Single.error<List<SkillIqLeader>>(Throwable())

        Mockito.`when`(services.getSkillLeaders()).thenReturn(testSingle)

        viewModel.refreshDataSkillLeaders()

        Assert.assertEquals(null, viewModel.skillLeaders.value?.size)
        Assert.assertEquals(false, viewModel.loading.value)
        Assert.assertEquals(true, viewModel.loadError.value)


    }

    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run()}, true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { scheduler -> immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> immediate }
    }
}