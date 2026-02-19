package io.eatda.core.service.promotion

import io.eatda.infrastructure.persistance.repository.promotion.PromotionConditionRepository
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