package com.iacovelli.repository.facts.data

import com.iacovelli.repository.facts.domain.Fact

interface Repository {
    suspend fun getFact(): Fact
}

class RepositoryImpl(
    private val remoteService: CatFactRemoteService,
    private val cacheService: CatFactCacheService
): Repository {

    override suspend fun getFact(): Fact {
        return try {
            remoteService.getFacts().toListOfFacts().random()
        } catch(exception: Exception) {
            Fact(cacheService.getFacts().random())
        }
    }
}
