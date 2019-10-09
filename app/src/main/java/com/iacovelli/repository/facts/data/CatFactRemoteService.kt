package com.iacovelli.repository.facts.data

import retrofit2.http.GET

interface CatFactRemoteService {

    @GET("/facts")
    suspend fun getFacts(): AllFactsResponse
}