package io.promoflow.infrastructure.persistance.entity.promotion.condition

import io.promoflow.core.domain.Order
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.*

@Entity
@DiscriminatorValue("ITEM_CATEGORY")
class ItemCategoryCondition(
    @ElementCollection
    @CollectionTable(
        name = "item_categorys",
        joinColumns = [JoinColumn(name = "promotion_conditions_id")]
    )
    val categoryIds: MutableSet<String> = mutableSetOf(),
    promotionRule: PromotionRule
) : PromotionCondition(promotionRule = promotionRule) {
    override fun isSatisfied(order: Order): Boolean = order.items.any { it.categoryId in categoryIds }
}