package com.faysal.assignementapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.faysal.assignementapp.data.models.User
import com.faysal.assignementapp.databinding.ActivityMainBinding
import com.faysal.assignementapp.main.MainViewModel
import com.faysal.assignementapp.ui.adapter.AdapterUserList
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

private const val TAG = "MainActivity"


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), AdapterUserList.OnItemClickListener {


    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapterUser: AdapterUserList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        setupViewsActions()

    }

    private fun setupViews() {


        adapterUser = AdapterUserList()
        binding.userGridView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = adapterUser
        }
        adapterUser.setOnItemClickListener(this)


        viewModel.getRandomUserListResponse()
    }

    private fun setupViewsActions() {
        lifecycleScope.launchWhenStarted {
            viewModel.user.collect { event ->
                when (event) {
                    is MainViewModel.ResponseEvent.Success -> {
                        event.data?.let {
                            adapterUser.updateUserList(it)
                        }
                        binding.loadingView.visibility = View.GONE
                    }
                    is MainViewModel.ResponseEvent.Failure -> {
                        Toast.makeText(
                            applicationContext,
                            "${event.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.loadingView.visibility = View.GONE
                    }
                    is MainViewModel.ResponseEvent.Loading -> {
                        binding.loadingView.visibility = View.VISIBLE
                    }
                    else -> Unit
                }

            }
        }
    }

    override fun onItemClicked(user: User, pos: Int) {
        val params = Gson().toJson(user)
        startActivity(
            Intent(this, ActivityDetails::class.java).putExtra("params", params)
        )
    }

}

