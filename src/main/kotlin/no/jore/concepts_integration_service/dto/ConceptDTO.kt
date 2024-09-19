package no.jore.concepts_integration_service.dto

import no.jore.concepts_integration_service.response.ConceptResponse
import java.time.LocalDate

data class ConceptDTO(
    val id: String,
    val subject: String,
    val prefLabel: String,
    val altLabel: String,
    val definition: Definition,
)

fun ConceptResponse.toDTO() = ConceptDTO(
    id = this.id,
    subject = this.subjectEnglish,
    prefLabel = preferredLabelEnglish,
    altLabel = alternativeLabelEnglish,
    definition = Definition(
        tekst = this.definitionEnglish,
        lastUpdated = LocalDate.parse(this.definitionLastUpdated)
    )
)

data class Definition(val tekst: String, val lastUpdated: LocalDate)
