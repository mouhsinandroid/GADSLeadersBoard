package com.mouhsinbourqaiba.android.gadsleadersboard.view.home.learner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mouhsinbourqaiba.android.gadsleadersboard.R
import com.mouhsinbourqaiba.android.gadsleadersboard.model.LearningLeader
import kotlinx.android.synthetic.main.fragment_list_learners.*


class ListLearnersFragment : Fragment() {

    private lateinit var viewModel: ListLearnersViewModel
    private val listAdapter = LearnerListAdapter(arrayListOf())

    private val learnerListDataObserver = Observer<List<LearningLeader>> { list ->
        list?.let {
            learnersList.visibility = View.VISIBLE
            listAdapter.updateLearnersList(list.sortedByDescending { it.hours })
        }
    }

    private val loadingDataObserver = Observer<Boolean> { isLoading ->
        loadingView.visibility = if(isLoading) View.VISIBLE else View.GONE
        if(isLoading) {
            listError.visibility = View.GONE
            learnersList.visibility = View.GONE
        }
    }

    private val errorLiveDataObserver = Observer<Boolean> { isError ->
        listError.visibility = if(isError) View.VISIBLE else View.GONE
        if(isError) {
            loadingView.visibility = View.GONE
            learnersList.visibility = View.GONE
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_learners, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListLearnersViewModel::class.java)
        viewModel.learners.observe(this, learnerListDataObserver)
        viewModel.loading.observe(this, loadingDataObserver)
        viewModel.loadError.observe(this, errorLiveDataObserver)
        viewModel.refreshDataLearners()

        learnersList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = listAdapter
        }

        refreshLayoutLearner.setOnRefreshListener {

            learnersList.visibility = View.GONE
            listError.visibility = View.GONE
            loadingView.visibility = View.VISIBLE

            viewModel.refreshDataLearners()

            refreshLayoutLearner.isRefreshing = false
        }

    }

}