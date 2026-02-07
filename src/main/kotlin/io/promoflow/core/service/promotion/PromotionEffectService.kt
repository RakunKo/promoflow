package io.promoflow.core.service.promotion

import io.promoflow.infrastructure.persistance.entity.promotion.effect.PromotionEffect
import io.promoflow.infrastructure.persistance.repository.promotion.PromotionEffectRepository
import io.promoflow.infrastructure.persistance.repository.promotion.PromotionRuleRepository
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