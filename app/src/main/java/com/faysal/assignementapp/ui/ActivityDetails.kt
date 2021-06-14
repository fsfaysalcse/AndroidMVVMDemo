package com.faysal.assignementapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import coil.transform.CircleCropTransformation
import com.faysal.assignementapp.R
import com.faysal.assignementapp.databinding.ActivityDetailsBinding
import com.google.gson.Gson
import java.lang.Exception
import android.content.Intent
import com.faysal.assignementapp.data.models.User


class ActivityDetails : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        binding.btnBack.setOnClickListener { finish() }

        if (intent !=null){
            try {
                val params = intent.getStringExtra("params")
                user = Gson().fromJson(params,User::class.java)
            }catch (e : Exception){
                e.printStackTrace()
            }
        }

        user?.let {
            it.name?.let { binding.tvName.text = "${it.title} ${it.first} ${it.last} " }

            it.location?.let {
                binding.tvCountry.text = "${it.country}"
                binding.tvAddress.text = "${it.city},${it.state},${it.country} "
            }

            it.picture?.let {
                binding.imgProfile.load(it.large) {
                    transformations(CircleCropTransformation())
                    crossfade(true)
                    placeholder(R.drawable.ic_baseline_person_24)
                }
            }


            it.email?.let { email->
                binding.tvEmail.text = "$email"

                binding.emailLayout.setOnClickListener { gotoEmail(email) }

            }

            it.phone?.let { binding.tvPhone.text = "$it" }




        }



    }

    private fun gotoEmail(email: String) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "plain/text"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("$email"))
            putExtra(Intent.EXTRA_SUBJECT, "From Friends App")
        }
        startActivity(Intent.createChooser(intent, ""))
    }


}