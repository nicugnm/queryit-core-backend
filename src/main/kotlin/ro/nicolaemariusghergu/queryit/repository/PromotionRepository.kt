package ro.nicolaemariusghergu.queryit.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import ro.nicolaemariusghergu.queryit.model.Promotion

@Transactional
interface PromotionRepository : JpaRepository<Promotion, Long>