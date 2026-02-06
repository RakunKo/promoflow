package io.promoflow.infrastructure.persistance.entity.promotion.condition

import io.promoflow.core.domain.Order
import io.promoflow.infrastructure.persistance.base.BaseEntity
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.*

@Entity
@Table(name = "promotion_conditions")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
    name = "condition_type",
    discriminatorType = DiscriminatorType.STRING
)
abstract class PromotionCondition(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_rule_id")
    open var promotionRule: PromotionRule
): BaseEntity() {
    abstract fun isSatisfied(order: Order): Boolean
}