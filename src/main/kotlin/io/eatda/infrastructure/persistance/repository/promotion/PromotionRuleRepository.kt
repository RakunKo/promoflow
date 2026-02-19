package io.eatda.infrastructure.persistance.repository.promotion

import io.eatda.infrastructure.persistance.entity.promotion.PromotionRule
import io.eatda.infrastructure.persistance.repository.promotion.custom.PromotionRuleDslRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PromotionRuleRepository: JpaRepository<PromotionRule, UUID>, PromotionRuleDslRepository {
    fun findPromotionRulesByPromotionId(id: UUID): List<PromotionRule>
}