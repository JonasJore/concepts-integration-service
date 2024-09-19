package no.jore.concepts_integration_service.integrations.conceptsApi

import no.jore.concepts_integration_service.dto.NewPageDTO
import no.jore.concepts_integration_service.response.ConceptResponse
import no.jore.concepts_integration_service.response.PageOfConceptsResponse
import no.jore.concepts_integration_service.response.TotalPagesOfContentResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import kotlin.Exception

@Service
class Adapter(
    private val restTemplate: RestTemplate,
) {
    @Value("\${baseUrl}")
    private lateinit var baseUrl: String
    private val searchTerm = if (isCachingEnabled) "Added" else "Searched"

    fun getAllConcepts(): List<ConceptResponse> {
        val totalPages = getTotalPagesOfConcepts()
        totalPages.let { totalPagesOfContent ->
            val list: MutableList<List<ConceptResponse>> = mutableListOf()
            (0..totalPagesOfContent.totalPages).forEach { page ->
                val pageOfConceptsResponse = restTemplate.postForEntity(
                    "$baseUrl/concepts",
                    NewPageDTO(page = page),
                    PageOfConceptsResponse::class.java
                )
                pageOfConceptsResponse.body?.let { list.add(it.conceptResponses) }
                println("$searchTerm concepts from page $page...")
            }

            return list.flatten()
        }
    }

    fun searchForId(id: String): ConceptResponse {
        val totalPagesOfContent = getTotalPagesOfConcepts().totalPages
        (0..totalPagesOfContent).forEach { page ->
            val pageOfConceptsResponse = restTemplate.postForEntity(
                "$baseUrl/concepts",
                NewPageDTO(page = page),
                PageOfConceptsResponse::class.java
            )
            pageOfConceptsResponse.body?.let { conceptResponse ->
                val conceptById = conceptResponse.conceptResponses.find { it.id == id }
                if (conceptById != null) {
                    return conceptById
                }
                println("Nothing found on page $page, moving on...")
            }
        }
        throw Exception("nothing found")
    }


    private fun getTotalPagesOfConcepts(): TotalPagesOfContentResponse = restTemplate.postForEntity(
        "$baseUrl/concepts",
        null,
        TotalPagesOfContentResponse::class.java
    ).body ?: TotalPagesOfContentResponse(totalPages = 0)
}


