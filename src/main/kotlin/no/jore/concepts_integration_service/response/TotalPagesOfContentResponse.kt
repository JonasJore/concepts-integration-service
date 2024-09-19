package no.jore.concepts_integration_service.response

import com.fasterxml.jackson.annotation.JsonProperty

data class TotalPagesOfContentResponse(
    @JsonProperty("total_pages") val totalPages: Int
)