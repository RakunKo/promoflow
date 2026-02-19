package io.eatda.infrastructure.persistance.repository.promotion.custom

import java.util.*

interface PromotionRuleDslRepository {
    fun deletePromotionRules(
        promotionRuleIds: List<UUID>
    ): Long
}