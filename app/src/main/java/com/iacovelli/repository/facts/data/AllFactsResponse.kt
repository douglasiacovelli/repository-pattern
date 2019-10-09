package com.iacovelli.repository.facts.data

import com.iacovelli.repository.facts.domain.Fact

data class AllFactsResponse(
    val all: List<FactResponse>
) {
    fun toListOfFacts(): List<Fact> {
        return all.map { it.toFact() }
    }
}

data class FactResponse(
    val text: String
) {
    fun toFact() = Fact(text = text)
}