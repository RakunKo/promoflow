package io.promoflow.infrastructure.persistance.repository.promotion

import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import io.promoflow.infrastructure.persistance.repository.dsl.PromotionRuleDslRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PromotionRuleRepository: JpaRepository<PromotionRule, UUID>, PromotionRuleDslRepository {
}