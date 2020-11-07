package com.puj.medicalconcepts.controller

import com.puj.medicalconcepts.domain.concepts.Concept
import com.puj.medicalconcepts.dto.concepts.CreateConceptDto
import com.puj.medicalconcepts.dto.concepts.ConceptDto
import com.puj.medicalconcepts.service.ConceptService

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.data.web.SortDefault
import org.springframework.data.web.PageableDefault
import javax.validation.Valid
import javax.servlet.http.HttpServletRequest
import java.security.Principal
import java.util.Date
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CrossOrigin
@RequestMapping("/concept") //Puede ir /concept
@RestController
class ConceptController(private val conceptService: ConceptService) {
    companion object {
        val logger = LoggerFactory.getLogger(ConceptController::class.java)!!
    }

    
    // BUSCAR POR CAMPOS DINÁMICOS
    @GetMapping(
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun getAllConceptDinamic(@RequestParam(value = "concept_id", required = false) concept_id : Int?,
                            @RequestParam(value = "vocabulary_id", required = false) vocabulary_id : String?,
                            @RequestParam(value = "domain_id", required = false) domain_id : String?,
                            @RequestParam(value = "short_desc", required = false) short_desc : String?,
                            @SortDefault.SortDefaults(
                                SortDefault(sort = arrayOf("concept_id"),
                                            direction = Sort.Direction.ASC),
                                SortDefault(sort = arrayOf("vocabulary_id"),
                                            direction = Sort.Direction.ASC),
                                SortDefault(sort = arrayOf("domain_id"),
                                            direction = Sort.Direction.ASC),
                                SortDefault(sort = arrayOf("short_desc"),
                                            direction = Sort.Direction.ASC)
                            )
                            @PageableDefault(size = 100)
                            pageable: Pageable): ResponseEntity<*> {
                                return conceptService.getAllConceptDinamic(concept_id, vocabulary_id, domain_id,short_desc)
                            }

    
    // AGREGAR UN CONCEPTO
    @PostMapping(
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun create(@RequestBody @Valid createConceptDto: CreateConceptDto, 
               @RequestHeader(value="authorization", required=true) authorization: String): ResponseEntity<*>
        = conceptService.create(createConceptDto)

    
    // ACTUALIZAR UN CONCEPTO
    @PutMapping(
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun update(@RequestBody @Valid conceptDto: ConceptDto,
                @RequestHeader(value="authorization", required=true) authorization: String): ResponseEntity<*>
        = conceptService.update(conceptDto)
        

    // BORRAR UN CONCEPTO
    @DeleteMapping(
        value = ["/{concept_id}"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun delete(@PathVariable concept_id: Int,
                @RequestHeader(value="authorization", required=true) authorization: String): ResponseEntity<*>
        = conceptService.delete(concept_id)
}