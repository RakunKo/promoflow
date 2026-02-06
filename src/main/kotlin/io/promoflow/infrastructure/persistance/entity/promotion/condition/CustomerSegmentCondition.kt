package io.promoflow.infrastructure.persistance.entity.promotion.condition

import io.promoflow.core.domain.Order
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("CUSTOMER_SEGMENT")
class CustomerSegmentCondition(
    val segmentName: String,
    promotionRule: PromotionRule
) : PromotionCondition(promotionRule = promotionRule) {
    override fun isSatisfied(order: Order): Boolean = order.customer.segment == segmentName
}