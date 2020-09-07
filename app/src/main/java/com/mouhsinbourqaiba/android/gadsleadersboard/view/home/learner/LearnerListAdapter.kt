package com.mouhsinbourqaiba.android.gadsleadersboard.view.home.learner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mouhsinbourqaiba.android.gadsleadersboard.R
import com.mouhsinbourqaiba.android.gadsleadersboard.databinding.ItemLearningLeaderBinding
import com.mouhsinbourqaiba.android.gadsleadersboard.model.LearningLeader

class LearnerListAdapter(private val learnerList: ArrayList<LearningLeader>): RecyclerView.Adapter<LearnerListAdapter.LearnerViewHolder>() {

    fun updateLearnersList(newAnimalList: List<LearningLeader>) {
        learnerList.clear()
        learnerList.addAll(newAnimalList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemLearningLeaderBinding>(inflater, R.layout.item_learning_leader, parent, false)

        return LearnerViewHolder(binding)
    }

    override fun getItemCount() = learnerList.size

    override fun onBindViewHolder(holder: LearnerViewHolder, position: Int) {
        holder.view.learner = learnerList[position]
    }

    class LearnerViewHolder(var view: ItemLearningLeaderBinding): RecyclerView.ViewHolder(view.root)


}