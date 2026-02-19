package io.eatda.core.service.promotion

import io.eatda.infrastructure.persistance.repository.promotion.PromotionEffectRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PromotionEffectService(
    private val promotionRuleRepository: PromotionEffectRepository
) {
    // soft delete, bulk delete
    fun deletePromotionEffects(
        promotionEffectIds: List<UUID>,
    ) {
        promotionRuleRepository.deletePromotionEffects(
            promotionEffectIds = promotionEffectIds
        )
    }

}