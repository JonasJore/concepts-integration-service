package no.jore.concepts_integration_service

import no.jore.concepts_integration_service.integrations.conceptsApi.Adapter
import no.jore.concepts_integration_service.integrations.conceptsApi.isCachingEnabled
import no.jore.concepts_integration_service.dto.ConceptDTO
import no.jore.concepts_integration_service.dto.toDTO
import org.springframework.stereotype.Service

@Service
class Service(
    private val adapter: Adapter,
    private val customCache: CustomCache
) {
    fun getAllConcepts(): List<ConceptDTO> {
        return if (isCachingEnabled) {
            customCache.getAll().map { it.toDTO() }
        } else {
            // TODO: return all concepts directly from api
            // HACK: This works but is really slow
            adapter.getAllConcepts().map { it.toDTO() }
        }
    }

    fun getConceptById(id: String): ConceptDTO {
        return if (isCachingEnabled) {
            customCache.getById(id).toDTO()
        } else {
            adapter.searchForId(id).toDTO()
        }
    }
}