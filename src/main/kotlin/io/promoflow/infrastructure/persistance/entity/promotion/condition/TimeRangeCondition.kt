
package io.promoflow.infrastructure.persistance.entity.promotion.condition

import io.promoflow.core.domain.Order
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.time.DayOfWeek
import java.time.LocalTime

@Entity
@DiscriminatorValue("TIME_RANGE")
class TimeRangeCondition(
    val dayOfWeek: DayOfWeek?,
    val startTime: LocalTime,
    val endTime: LocalTime,
    promotionRule: PromotionRule
) : PromotionCondition(promotionRule = promotionRule) {
    override fun isSatisfied(order: Order): Boolean {
        val isCorrectDay = dayOfWeek == null || order.orderedAt.dayOfWeek == dayOfWeek

        val orderTime = order.orderedAt.toLocalTime()
        val isWithinTime = orderTime in startTime..endTime

        return isCorrectDay && isWithinTime
    }
}