package io.eatda.infrastructure.persistance.entity.promotion.effect

import io.eatda.infrastructure.persistance.base.BaseEntity
import io.eatda.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "promotion_effects")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
    name = "effect_type",
    discriminatorType = DiscriminatorType.STRING
)
abstract class PromotionEffect(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_rule_id")
    open var promotionRule: PromotionRule
): BaseEntity() {
    abstract fun effect(amount: BigDecimal): BigDecimal
}