package io.promoflow.infrastructure.persistance.entity.promotion.condition

import io.promoflow.core.domain.Order
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.*

@Entity
@DiscriminatorValue("PAYMENT_METHOD")
class PaymentMethodCondition(
    @ElementCollection
    @CollectionTable(
        name = "payment_methods",
        joinColumns = [JoinColumn(name = "promotion_conditions_id")]
    )
    val paymentMethod: List<String>,
    promotionRule: PromotionRule
) : PromotionCondition(promotionRule = promotionRule) {
    override fun isSatisfied(order: Order): Boolean = paymentMethod.contains(order.paymentMethod)
}