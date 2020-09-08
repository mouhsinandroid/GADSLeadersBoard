package com.mouhsinbourqaiba.android.gadsleadersboard.view.home.skilliq

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mouhsinbourqaiba.android.gadsleadersboard.R
import com.mouhsinbourqaiba.android.gadsleadersboard.model.SkillIqLeader
import kotlinx.android.synthetic.main.fragment_list_learners.listError
import kotlinx.android.synthetic.main.fragment_list_learners.loadingView
import kotlinx.android.synthetic.main.fragment_list_learners.refreshLayoutLearner
import kotlinx.android.synthetic.main.fragment_list_skill_leaders.*

class ListSkillLeadersFragment: Fragment() {

    private lateinit var viewModel: ListSkillLeadersViewModel
    private val listAdapter = SkillLeadersListAdapter(arrayListOf())

    private val skillLeadersListDataObserver = Observer<List<SkillIqLeader>> { list ->
        list?.let {
            skillLeadersList.visibility = View.VISIBLE
            listAdapter.updateSkillLeadersList(list.sortedByDescending { it.score })
        }
    }

    private val loadingDataObserver = Observer<Boolean> { isLoading ->
        loadingView.visibility = if(isLoading) View.VISIBLE else View.GONE
        if(isLoading) {
            listError.visibility = View.GONE
            skillLeadersList.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        listError.visibility = if(isError) View.VISIBLE else View.GONE
        if(isError) {
            loadingView.visibility = View.GONE
            skillLeadersList.visibility = View.GONE
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_skill_leaders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListSkillLeadersViewModel::class.java)
        viewModel.skillLeaders.observe(this, skillLeadersListDataObserver)
        viewModel.loading.observe(this, loadingDataObserver)
        viewModel.loadError.observe(this, errorLiveDataObserver)
        viewModel.refreshDataSkillLeaders()

        skillLeadersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        refreshLayoutLearner.setOnRefreshListener {

            skillLeadersList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE

            viewModel.refreshDataSkillLeaders()

            refreshLayoutLearner.isRefreshing = false
        }

    }
}