package com.mouhsinbourqaiba.android.gadsleadersboard.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

object actionGo {

    fun addFragment(
        activity: AppCompatActivity,
        fragment: Fragment?, content: Int, addToBackStack: Boolean
    ) {
        val fragmentManager: FragmentManager = activity.supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        if (!addToBackStack) {
            transaction.disallowAddToBackStack()
        } else {
            transaction.addToBackStack(null)
        }
        transaction.replace(content, fragment!!)
        transaction.commit()
    }
}