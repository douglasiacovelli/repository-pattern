package com.iacovelli.repository.facts.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iacovelli.repository.CustomApplication
import com.iacovelli.repository.R
import com.iacovelli.repository.facts.data.CatFactCacheServiceImpl
import com.iacovelli.repository.facts.data.CatFactRemoteService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private val remoteService by lazy {
        (application as CustomApplication).retrofit
            .create(CatFactRemoteService::class.java)
    }
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
        swipeRefresh.setOnRefreshListener {
            viewModel.fetchData()
        }
    }

    private fun setupObservers() {
        viewModel.cats.observe(this, Observer {
            factTextView.text = it.text
            swipeRefresh.isRefreshing = false
        })
    }
}
