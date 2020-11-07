package com.puj.medicalconcepts.dto.concepts

import com.puj.medicalconcepts.domain.concepts.Concept

data class ConceptDto(
    val id: Int,
    val pxordx: String,
    val oldpxordx: String,
    val codetype: String,
    val concept_class_id: String,
    val concept_id: Int,
    val vocabulary_id: String,
    val domain_id: String,
    val track: String,
    val standard_concept: String,
    val code: String,
    val codewithperiods: String,
    val codescheme: String,
    val long_desc: String,
    val short_desc: String,
    val code_status: String,
    val code_change: String,
    val code_change_year: Int,
    val code_planned_type: String,
    val code_billing_status: String,
    val code_cms_claim_status: String,
    val sex_cd: String,
    val anat_or_cond: String,
    val poa_code_status: String,
    val poa_code_change: String,
    val poa_code_change_year: Int,
    val valid_start_date: Int,
    val valid_end_date: Int,
    val invalid_reason: String,
    val create_dt: Int
) {
    companion object {
        fun convert(concept: Concept): ConceptDto {
            val dto = ConceptDto(
                id = concept.id,
                pxordx = concept.pxordx,
                oldpxordx = concept.oldpxordx,
                codetype = concept.codetype,
                concept_class_id = concept.concept_class_id,
                concept_id = concept.concept_id,
                vocabulary_id = concept.vocabulary_id,
                domain_id = concept.domain_id,
                track = concept.track,
                standard_concept = concept.standard_concept,
                code = concept.code,
                codewithperiods = concept.codewithperiods,
                codescheme = concept.codescheme,
                long_desc = concept.long_desc,
                short_desc = concept.short_desc,
                code_status = concept.code_status,
                code_change = concept.code_change,
                code_change_year = concept.code_change_year,
                code_planned_type = concept.code_planned_type,
                code_billing_status = concept.code_billing_status,
                code_cms_claim_status = concept.code_cms_claim_status,
                sex_cd = concept.sex_cd,
                anat_or_cond = concept.anat_or_cond,
                poa_code_status = concept.poa_code_status,
                poa_code_change = concept.poa_code_change,
                poa_code_change_year = concept.poa_code_change_year,
                valid_start_date = concept.valid_start_date,
                valid_end_date = concept.valid_end_date,
                invalid_reason = concept.invalid_reason,
                create_dt = concept.create_dt
            )
            return dto
        }
    }
}