package com.iacovelli.repository.facts.data

interface CatFactCacheService {
    fun getFacts(): List<String>
}

class CatFactCacheServiceImpl: CatFactCacheService {
    override fun getFacts(): List<String> {
        return listOf("Cats like eating raw meat")
    }
}