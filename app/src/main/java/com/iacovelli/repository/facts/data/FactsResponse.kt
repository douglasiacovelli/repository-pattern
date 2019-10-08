package com.iacovelli.repository.facts.data

import com.iacovelli.repository.facts.domain.Fact

data class FactsResponse(
    val all: List<FactResponse>
) {
    fun toModel(): List<Fact> {
        return all.map { it.toModel() }
    }
}

data class FactResponse(
    val text: String
) {
    fun toModel(): Fact {
        return Fact(text = text)
    }
}