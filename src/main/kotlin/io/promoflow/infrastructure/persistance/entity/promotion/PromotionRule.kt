package io.promoflow.infrastructure.persistance.entity.promotion

import io.promoflow.infrastructure.persistance.base.BaseEntity
import io.promoflow.infrastructure.persistance.entity.promotion.condition.PromotionCondition
import io.promoflow.infrastructure.persistance.entity.promotion.effect.PromotionEffect
import jakarta.persistence.*

@Entity
@Table(name = "promotion_rules")
class PromotionRule(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_id")
    val promotion: Promotion,

    @Column(nullable = false, length = 50)
    var name: String,

    @OneToMany(mappedBy = "promotionRule", cascade = [CascadeType.ALL], orphanRemoval = true)
    val conditions: MutableList<PromotionCondition> = mutableListOf(),

    @OneToMany(mappedBy = "promotionRule", cascade = [CascadeType.ALL], orphanRemoval = true)
    val effects: MutableList<PromotionEffect> = mutableListOf(),

    val priority: Int = 0
): BaseEntity()