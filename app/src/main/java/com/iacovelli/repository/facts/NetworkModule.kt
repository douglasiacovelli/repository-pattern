package com.iacovelli.repository.facts

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class NetworkModule {

    fun provideRetrofit(): Retrofit {
        val moshiConverter = MoshiConverterFactory.create()
        return Retrofit.Builder()
            .baseUrl("https://cat-fact.herokuapp.com")
            .addConverterFactory(moshiConverter)
            .build()
    }
}