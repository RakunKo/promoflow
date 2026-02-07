package io.promoflow.core.service.promotion

import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import io.promoflow.infrastructure.persistance.repository.promotion.PromotionRuleRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PromotionRuleService(
    private val promotionRuleRepository: PromotionRuleRepository
) {
    // soft delete, bulk delete
    fun deletePromotionRules(
        promotionRuleIds: List<UUID>,
    ) {
        promotionRuleRepository.deletePromotionRules(
            promotionRuleIds = promotionRuleIds
        )
    }

}