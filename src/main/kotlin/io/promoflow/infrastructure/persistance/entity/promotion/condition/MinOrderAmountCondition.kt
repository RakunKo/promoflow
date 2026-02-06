package io.promoflow.infrastructure.persistance.entity.promotion.condition

import io.promoflow.core.domain.Order
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@DiscriminatorValue("MIN_ORDER_AMOUNT")
class MinOrderAmountCondition(
    @Column(precision = 19, scale = 4)
    var minAmount: BigDecimal,
    promotionRule: PromotionRule
): PromotionCondition(promotionRule = promotionRule) {
    override fun isSatisfied(order: Order): Boolean = minAmount <= order.totalAmount
}