package io.promoflow.infrastructure.persistance.repository.promotion

import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.repository.promotion.custom.PromotionDslRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PromotionRepository: JpaRepository<Promotion, UUID>, PromotionDslRepository {
    fun existsUserByName(name: String): Boolean
}