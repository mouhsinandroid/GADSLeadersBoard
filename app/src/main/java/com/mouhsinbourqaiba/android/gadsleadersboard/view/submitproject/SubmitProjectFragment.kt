package com.mouhsinbourqaiba.android.gadsleadersboard.view.submitproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mouhsinbourqaiba.android.gadsleadersboard.R
import com.mouhsinbourqaiba.android.gadsleadersboard.model.SkillIqLeader
import kotlinx.android.synthetic.main.fragment_list_learners.*
import kotlinx.android.synthetic.main.fragment_list_skill_leaders.*
import kotlinx.android.synthetic.main.fragment_list_skill_leaders.listError
import kotlinx.android.synthetic.main.fragment_list_skill_leaders.loadingView
import kotlinx.android.synthetic.main.fragment_submit_project.*


class SubmitProjectFragment : Fragment() {

    private lateinit var viewModel: SubmitProjectViewModel


    private val loadingObserver = Observer<Boolean> { isLoading ->
        loadingPostForm.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private val errorSendFormObserver = Observer<Boolean> { isError ->
        if(isError) {
            loadingPostForm.visibility = View.GONE
            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
        }
    }

    private val successObserver = Observer<Boolean> { isLoading ->
        loadingPostForm.visibility = if(isLoading) View.VISIBLE else View.GONE
        if(isLoading) {
            loadingPostForm.visibility = View.GONE
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
            activity!!.onBackPressed()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_submit_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener { activity!!.onBackPressed() }

        viewModel = ViewModelProviders.of(this).get(SubmitProjectViewModel::class.java)


        submitProjectButton.setOnClickListener {

            val firstName = etFname.text.toString().trim()
            val lastName = etLname.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val link = etProject.text.toString().trim()

            viewModel.postFormProject(firstName, lastName, email, link)
            viewModel.errorStatus.observe(this, errorSendFormObserver)
            viewModel.loadingStatus.observe(this, loadingObserver)
            viewModel.successStatus.observe(this, successObserver)

        }

    }

}