package com.puj.medicalconcepts.service

import com.puj.medicalconcepts.domain.concepts.Concept
import com.puj.medicalconcepts.dto.concepts.ConceptDto
import com.puj.medicalconcepts.dto.concepts.CreateConceptDto
import com.puj.medicalconcepts.dto.IdResponseDto
import com.puj.medicalconcepts.repository.concepts.ConceptRepository
import com.puj.medicalconcepts.repository.concepts.ConceptDinamicSpecification //Nuevo

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Page
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Service
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.slf4j.LoggerFactory
import java.io.Serializable
import java.util.*

@Service
class ConceptService(private val conceptRepository: ConceptRepository) {
    companion object {
        val LOG = LoggerFactory.getLogger(ConceptService::class.java)!!
    }

    fun count(): Long {
        return conceptRepository.count()
    }


    // REALIZAR CONSULTAS ANIDADAS Y DINÁMICAS
    fun getAllConceptDinamic(concept_id : Int?,
                            vocabulary_id : String?,
                            domain_id : String?,
                            short_desc : String?): ResponseEntity<*> {
        LOG.info("Si entra")
        val conceptDinamic = conceptRepository.findAll(ConceptDinamicSpecification(
            concept_id, vocabulary_id, domain_id, short_desc
        ))
        return ResponseEntity.ok(conceptDinamic)
    }

    // BORRAR UN CONCEPTO POR SU CONCEPT_ID
    fun delete(concept_id: Int): ResponseEntity<*> {
        if (!conceptRepository.existsByConceptId(concept_id)) {
            val messageError = "Concepto con concept_id: ${concept_id} no existe."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                                       HttpStatus.CONFLICT)
        }
        val tmpConcept = conceptRepository.findByConcept_Id(concept_id)
        conceptRepository.deleteById(tmpConcept!!.id)
        val messageOK = "Concepto con concepto_id ${concept_id} borrado exitosamente"
        LOG.info(messageOK)
        return ResponseEntity<Any>(messageOK, HttpStatus.ACCEPTED)
    }


    // ACTUALIZAR UN CONCEPTO POR SU CONCEPT_ID
    fun update(conceptDto: ConceptDto): ResponseEntity<*>  { 
        if (!conceptRepository.existsByConceptId(conceptDto.concept_id)) {
            val messageError = "Concepto con concept_id: ${conceptDto.concept_id} no existe."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                                       HttpStatus.CONFLICT)
        }
        val tmpConcept = conceptRepository.findByConcept_Id(conceptDto.concept_id)
        val concept = Concept(id = tmpConcept!!.id,
                                pxordx = conceptDto.pxordx,
                                oldpxordx = conceptDto.oldpxordx,
                                codetype = conceptDto.codetype,
                                concept_class_id = conceptDto.concept_class_id,
                                concept_id = conceptDto.concept_id,
                                vocabulary_id = conceptDto.vocabulary_id,
                                domain_id = conceptDto.domain_id,
                                track = conceptDto.track,
                                standard_concept = conceptDto.standard_concept,
                                code = conceptDto.code,
                                codewithperiods = conceptDto.codewithperiods,
                                codescheme = conceptDto.codescheme,
                                long_desc = conceptDto.long_desc,
                                short_desc = conceptDto.short_desc,
                                code_status = conceptDto.code_status,
                                code_change = conceptDto.code_change,
                                code_change_year = conceptDto.code_change_year,
                                code_planned_type = conceptDto.code_planned_type,
                                code_billing_status = conceptDto.code_billing_status,
                                code_cms_claim_status = conceptDto.code_cms_claim_status,
                                sex_cd = conceptDto.sex_cd,
                                anat_or_cond = conceptDto.anat_or_cond,
                                poa_code_status = conceptDto.poa_code_status,
                                poa_code_change = conceptDto.poa_code_change,
                                poa_code_change_year = conceptDto.poa_code_change_year,
                                valid_start_date = conceptDto.valid_start_date,
                                valid_end_date = conceptDto.valid_end_date,
                                invalid_reason = conceptDto.invalid_reason,
                                create_dt = conceptDto.create_dt)                    
        val conceptUpdated = conceptRepository.save(concept)
        val messageOK = "Concepto con concepto_id ${conceptDto.concept_id} actualizado exitosamente"
        LOG.info(messageOK)
        //return ResponseEntity.ok(conceptUpdated)
        return ResponseEntity<Any>(messageOK, HttpStatus.ACCEPTED)
    }


    // CREAR UN CONCEPTO NUEVO Y VALIDA QUE NO EXISTA PREVIAMENTE 
    fun create(createConceptDto: CreateConceptDto): ResponseEntity<*> {
        if (conceptRepository.existsByConceptId(createConceptDto.concept_id)) {
            val messageError = "Concepto con concept_id: ${createConceptDto.concept_id} ya existe."
            LOG.error(messageError)
            return ResponseEntity<Any>(messageError,
                                       HttpStatus.CONFLICT)
        }

        val concept = Concept(pxordx = createConceptDto.pxordx,
                        oldpxordx = createConceptDto.oldpxordx,
                        codetype = createConceptDto.codetype,
                        concept_class_id = createConceptDto.concept_class_id,
                        concept_id = createConceptDto.concept_id,
                        vocabulary_id = createConceptDto.vocabulary_id,
                        domain_id = createConceptDto.domain_id,
                        track = createConceptDto.track,
                        standard_concept = createConceptDto.standard_concept,
                        code = createConceptDto.code,
                        codewithperiods = createConceptDto.codewithperiods,
                        codescheme = createConceptDto.codescheme,
                        long_desc = createConceptDto.long_desc,
                        short_desc = createConceptDto.short_desc,
                        code_status = createConceptDto.code_status,
                        code_change = createConceptDto.code_change,
                        code_change_year = createConceptDto.code_change_year,
                        code_planned_type = createConceptDto.code_planned_type,
                        code_billing_status = createConceptDto.code_billing_status,
                        code_cms_claim_status = createConceptDto.code_cms_claim_status,
                        sex_cd = createConceptDto.sex_cd,
                        anat_or_cond = createConceptDto.anat_or_cond,
                        poa_code_status = createConceptDto.poa_code_status,
                        poa_code_change = createConceptDto.poa_code_change,
                        poa_code_change_year = createConceptDto.poa_code_change_year,
                        valid_start_date = createConceptDto.valid_start_date,
                        valid_end_date = createConceptDto.valid_end_date,
                        invalid_reason = createConceptDto.invalid_reason,
                        create_dt = createConceptDto.create_dt)
        val conceptSaved = conceptRepository.save(concept)
        LOG.info("Concepto ${createConceptDto.concept_id} creado exitosamente con el id ${conceptSaved.id}")

        val responseDto = IdResponseDto(conceptSaved.id.toLong())
        return ResponseEntity<IdResponseDto>(responseDto,
                                             HttpStatus.CREATED)
    }


}