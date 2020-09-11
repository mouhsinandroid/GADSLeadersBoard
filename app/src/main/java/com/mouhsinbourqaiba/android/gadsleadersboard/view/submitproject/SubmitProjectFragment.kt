package com.mouhsinbourqaiba.android.gadsleadersboard.view.submitproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.mouhsinbourqaiba.android.gadsleadersboard.R
import kotlinx.android.synthetic.main.fragment_submit_project.*


class SubmitProjectFragment : Fragment() {

    private lateinit var viewModel: SubmitProjectViewModel


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
            viewModel.postFormProject("test", "test", "mouh", "www.google.com")
            Toast.makeText(context, "click submit", Toast.LENGTH_SHORT).show()
        }

    }

}