package com.mouhsinbourqaiba.android.gadsleadersboard.view.home.skilliq

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mouhsinbourqaiba.android.gadsleadersboard.R
import com.mouhsinbourqaiba.android.gadsleadersboard.databinding.ItemSkillIqLeaderBindingImpl
import com.mouhsinbourqaiba.android.gadsleadersboard.model.SkillIqLeader

class SkillLeadersListAdapter(private val skillLeadersList: ArrayList<SkillIqLeader>):RecyclerView.Adapter<SkillLeadersListAdapter.skillLeaderViewHolder>() {

    fun updateSkillLeadersList(newSkillLeadersList: List<SkillIqLeader>) {
        skillLeadersList.clear()
        skillLeadersList.addAll(newSkillLeadersList)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): skillLeaderViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSkillIqLeaderBindingImpl>(inflate, R.layout.item_skill_iq_leader, parent, false)

        return skillLeaderViewHolder(binding)
    }

    override fun getItemCount() = skillLeadersList.size

    override fun onBindViewHolder(holder: skillLeaderViewHolder, position: Int) {

        holder.view.skillsLeaders = skillLeadersList[position]
    }

    class skillLeaderViewHolder(var view: ItemSkillIqLeaderBindingImpl): RecyclerView.ViewHolder(view.root)


}