package com.puj.medicalconcepts.domain.concepts

import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "concept")
data class Concept(
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    
    @Column(name = "pxordx", nullable = false)
    val pxordx: String = "",

    @Column(name = "oldpxordx", nullable = false)
    val oldpxordx: String = "",

    @Column(name = "codetype", nullable = false)
    val codetype: String = "",

    @Column(name = "concept_class_id", nullable = false)
    val concept_class_id: String = "",

    @Column(name = "concept_id", nullable = false)
    val concept_id: Int = 0,

    @Column(name = "vocabulary_id", nullable = false)
    val vocabulary_id: String = "",

    @Column(name = "domain_id", nullable = false)
    val domain_id: String = "",

    @Column(name = "track", nullable = false)
    val track: String = "",

    @Column(name = "standard_concept", nullable = false)
    val standard_concept: String = "",

    @Column(name = "code", nullable = false)
    val code: String = "",

    @Column(name = "codewithperiods", nullable = false)
    val codewithperiods: String = "",

    @Column(name = "codescheme", nullable = false)
    val codescheme: String = "",

    @Column(name = "long_desc", nullable = false)
    val long_desc: String = "",

    @Column(name = "short_desc", nullable = false)
    val short_desc: String = "",

    @Column(name = "code_status", nullable = false)
    val code_status: String = "",

    @Column(name = "code_change", nullable = false)
    val code_change: String = "",

    @Column(name = "code_change_year", nullable = false)
    val code_change_year: Int = 0,

    @Column(name = "code_planned_type", nullable = false)
    val code_planned_type: String = "",

    @Column(name = "code_billing_status", nullable = false)
    val code_billing_status: String = "",

    @Column(name = "code_cms_claim_status", nullable = false)
    val code_cms_claim_status: String = "",

    @Column(name = "sex_cd", nullable = false)
    val sex_cd: String = "",

    @Column(name = "anat_or_cond", nullable = false)
    val anat_or_cond: String = "",

    @Column(name = "poa_code_status", nullable = false)
    val poa_code_status: String = "",

    @Column(name = "poa_code_change", nullable = false)
    val poa_code_change: String = "",

    @Column(name = "poa_code_change_year", nullable = false)
    val poa_code_change_year: Int = 0,

    @Column(name = "valid_start_date", nullable = false)
    val valid_start_date: Int = 0,

    @Column(name = "valid_end_date", nullable = false)
    val valid_end_date: Int = 0,

    @Column(name = "invalid_reason", nullable = false)
    val invalid_reason: String = "",

    @Column(name = "create_dt", nullable = false)
    val create_dt: Int = 0

)