package no.jore.concepts_integration_service

import no.jore.concepts_integration_service.integrations.conceptsApi.Adapter
import no.jore.concepts_integration_service.integrations.conceptsApi.isCachingEnabled
import no.jore.concepts_integration_service.response.ConceptResponse
import org.springframework.stereotype.Component

@Component
class CustomCache(private val adapter: Adapter) {
    private final val conceptResponses = mutableListOf<ConceptResponse>()

    init {
        if (isCachingEnabled && conceptResponses.isEmpty())
            fillCache()
    }

    private fun fillCache() {
        if (conceptResponses.isEmpty()) {
            val response = adapter.getAllConcepts()
            response.forEach { add(it) }
        }
    }

    private fun alreadyAdded(newEntry: ConceptResponse): Boolean =
        conceptResponses.firstOrNull { it.id == newEntry.id } != null

    fun add(newEntry: ConceptResponse) {
        if (conceptResponses.isNotEmpty() && alreadyAdded(newEntry))
            throw IllegalArgumentException("Entry already exists in cache")

        conceptResponses.add(newEntry)
    }

    fun getAll(): List<ConceptResponse> = conceptResponses.toList()

    fun getById(id: String): ConceptResponse {
        return conceptResponses.first { it.id == id }
    }
}