package com.mouhsinbourqaiba.android.gadsleadersboard.util

import android.content.Context
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mouhsinbourqaiba.android.gadsleadersboard.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}

@BindingAdapter("android:imageUrl")
fun loadImage(view: ImageView, url: String) {
    view.loadImage(url, getProgressDrawable(view.context))
}

@BindingAdapter(value = ["hours", "country"], requireAll = false)
fun descLearner(view: TextView, hours: Int, country: String) {
    view.setText("$hours learning hours, $country.")
}

@BindingAdapter(value = ["score", "country"], requireAll = false)
fun descSkillLeaders(view: TextView, score: Int, country: String) {
    view.setText("$score skill IQ Score, $country.")
}

