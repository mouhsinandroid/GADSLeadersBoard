package com.mouhsinbourqaiba.android.gadsleadersboard.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mouhsinbourqaiba.android.gadsleadersboard.R
import com.mouhsinbourqaiba.android.gadsleadersboard.util.actionGo.addFragment
import com.mouhsinbourqaiba.android.gadsleadersboard.view.home.learner.ListLearnersFragment
import com.mouhsinbourqaiba.android.gadsleadersboard.view.home.skilliq.ListSkillLeadersFragment
import com.mouhsinbourqaiba.android.gadsleadersboard.view.submitproject.SubmitProjectFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        tvSubmit.setOnClickListener {
            val fragment = SubmitProjectFragment()
            addFragment(this, fragment, R.id.container, true)
        }

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ListLearnersFragment(), getString(R.string.tab_learners))
        adapter.addFragment(ListSkillLeadersFragment(), getString(R.string.tab_skill_leaders))
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }

}