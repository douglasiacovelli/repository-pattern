package com.iacovelli.repository.facts.presentation

import android.app.Activity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.iacovelli.repository.CustomApplication
import com.iacovelli.repository.R
import com.iacovelli.repository.facts.data.CatFactCacheServiceImpl
import com.iacovelli.repository.facts.data.CatFactRemoteService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private val remoteService by lazy { getRetrofit().create(CatFactRemoteService::class.java) }
    private val cacheService by lazy { CatFactCacheServiceImpl() }
    private val viewModel by viewModels<MainViewModel> { MainViewModel.Factory(remoteService, cacheService) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.fetchData()
        setupObservers()
        setupSwipeRefresh()
    }

    private fun setupSwipeRefresh() {
        swipeRefresh.setOnRefreshListener { viewModel.fetchData() }
    }

    private fun setupObservers() {
        viewModel.cats.observe(this, Observer {
            factTextView.text = it.text
            swipeRefresh.isRefreshing = false
        })
    }
}

fun Activity.getRetrofit(): Retrofit = (application as CustomApplication).retrofit
