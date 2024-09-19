package no.jore.concepts_integration_service.response

import com.fasterxml.jackson.annotation.JsonProperty

data class PageOfConceptsResponse(
    @JsonProperty("concepts") val conceptResponses: List<ConceptResponse>
)

data class ConceptResponse(
    @JsonProperty("id") val id: String,
    @JsonProperty("alternativeLabel_en") val alternativeLabelEnglish: String,
    @JsonProperty("alternativeLabel_nb") val alternativeLabel: String,
    @JsonProperty("alternativeLabel_nn") val alternativeLabelNewNorwegian: String,
    @JsonProperty("definition_en") val definitionEnglish: String,
    @JsonProperty("definition_nb") val definition: String,
    @JsonProperty("definition_nn") val definitionNewNorwegian: String,
    @JsonProperty("definition_lastUpdated") val definitionLastUpdated: String,
    @JsonProperty("preferredLabel_nb") val preferredLabel: String,
    @JsonProperty("preferredLabel_en") val preferredLabelEnglish: String,
    @JsonProperty("preferredLabel_nn") val preferredLabelNewNorwegian: String,
    @JsonProperty("subject_nb") val subject: String,
    @JsonProperty("subject_en") val subjectEnglish: String,
    @JsonProperty("subject_nn") val subjectNewNorwegian: String,
)