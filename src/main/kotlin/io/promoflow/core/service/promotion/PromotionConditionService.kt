package io.promoflow.core.service.promotion

import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import io.promoflow.infrastructure.persistance.entity.promotion.condition.PromotionCondition
import io.promoflow.infrastructure.persistance.repository.promotion.PromotionConditionRepository
import io.promoflow.infrastructure.persistance.repository.promotion.PromotionRuleRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PromotionConditionService(
    private val promotionConditionRepository: PromotionConditionRepository
) {
    // soft delete, bulk delete
    fun deletePromotionConditions(
        promotionConditionIds: List<UUID>,
    ) {
        promotionConditionRepository.deletePromotionConditions(
            promotionConditionIds = promotionConditionIds
        )
    }

}