package com.iacovelli.repository

import android.app.Application
import com.iacovelli.repository.facts.NetworkModule
import retrofit2.Retrofit

class CustomApplication: Application() {
    lateinit var retrofit: Retrofit
        private set

    override fun onCreate() {
        super.onCreate()
        retrofit = NetworkModule().provideRetrofit()
    }

}