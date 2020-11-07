package com.puj.medicalconcepts.repository.concepts

import com.puj.medicalconcepts.domain.concepts.Concept

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.*
import org.springframework.data.jpa.domain.Specification //Nuevo

import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import javax.persistence.criteria.JoinType
import javax.persistence.criteria.Subquery


@Repository
interface ConceptRepository : JpaRepository<Concept, Int>,
                           JpaSpecificationExecutor<Concept> {

    @Query("""
        SELECT COUNT(concept) > 0
        FROM Concept concept
        WHERE concept.concept_id = :concept_id
    """)
    fun existsByConceptId(@Param("concept_id") concept_id: Int): Boolean

    @Query("""
        SELECT concept
        FROM Concept concept
        WHERE concept.concept_id = :concept_id
    """)
    fun findByConcept_Id(@Param("concept_id") concept_id: Int): Concept?

}

class ConceptDinamicSpecification(private val concept_id: Int?,
                                  private val vocabulary_id: String?,
                                  private val domain_id: String?,
                                  private val short_desc: String?) :  Specification<Concept> {
    override fun toPredicate(root: Root<Concept>, query: CriteriaQuery<*>, cb : CriteriaBuilder): Predicate {
        val p = mutableListOf<Predicate>()
        concept_id?.let {p.add(cb.equal(root.get<Int>("concept_id"), concept_id)) }
        vocabulary_id?.let {p.add(cb.equal(root.get<String>("vocabulary_id"), vocabulary_id)) }
        domain_id?.let {p.add(cb.equal(root.get<String>("domain_id"), domain_id)) }
        short_desc?.let {p.add(cb.like(root.get<String>("short_desc"), "%$short_desc")) }
        return cb.and(*p.toTypedArray())
    }
}