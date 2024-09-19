package no.jore.concepts_integration_service

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/begrep")
class Controller(private val conceptsService: Service) {
    @GetMapping("")
    fun getConcepts() =
        ResponseEntity.ok(conceptsService.getAllConcepts())

    @GetMapping("/{id}")
    fun getConceptsById(@PathVariable id: String) =
        ResponseEntity.ok(conceptsService.getConceptById(id))
}