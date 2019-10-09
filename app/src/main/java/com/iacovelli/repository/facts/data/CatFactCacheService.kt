package com.iacovelli.repository.facts.data

interface CatFactCacheService {
    fun getFacts(): List<String>
}

class CatFactCacheServiceImpl: CatFactCacheService {
    override fun getFacts(): List<String> {
        return listOf("Cache: Cats like eating raw meat", "Cache: Young cats are called kittens")
    }
}