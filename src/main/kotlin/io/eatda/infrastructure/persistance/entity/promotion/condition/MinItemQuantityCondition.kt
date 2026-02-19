package io.eatda.infrastructure.persistance.entity.promotion.condition

import io.eatda.core.domain.Order
import io.eatda.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("MIN_ITEM_QUANTITY")
class MinItemQuantityCondition(
    var minQuantity: Int,
    promotionRule: PromotionRule
) : PromotionCondition(promotionRule = promotionRule) {
    override fun isSatisfied(order: Order): Boolean = minQuantity <= order.totalQuantity
}