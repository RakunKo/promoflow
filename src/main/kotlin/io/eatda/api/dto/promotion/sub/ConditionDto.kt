package io.eatda.api.dto.promotion.sub

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.eatda.infrastructure.persistance.entity.promotion.PromotionRule
import io.eatda.infrastructure.persistance.entity.promotion.condition.*
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalTime

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "conditionType"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = MinOrderAmountDto::class, name = "MIN_ORDER_AMOUNT"),
    JsonSubTypes.Type(value = ItemCategoryDto::class, name = "ITEM_CATEGORY"),
    JsonSubTypes.Type(value = CustomerSegmentDto::class, name = "CUSTOMER_SEGMENT"),
    JsonSubTypes.Type(value = MinItemQuantityDto::class, name = "MIN_ITEM_QUANTITY"),
    JsonSubTypes.Type(value = PaymentMethodDto::class, name = "PAYMENT_METHOD"),
    JsonSubTypes.Type(value = TimeRangeDto::class, name = "TIME_RANGE"),
)
abstract class ConditionDto {
    abstract fun toEntity(promotionRule: PromotionRule): PromotionCondition
    companion object {
        fun fromEntity(entity: PromotionCondition): ConditionDto = when (entity) {
            is MinOrderAmountCondition -> MinOrderAmountDto(entity.minAmount)
            is ItemCategoryCondition -> ItemCategoryDto(entity.categoryIds.toMutableSet())
            is CustomerSegmentCondition -> CustomerSegmentDto(entity.segmentName)
            is MinItemQuantityCondition -> MinItemQuantityDto(entity.minQuantity)
            is PaymentMethodCondition -> PaymentMethodDto(entity.paymentMethod)
            is TimeRangeCondition -> TimeRangeDto(entity.dayOfWeek, entity.startTime, entity.endTime)
            else -> throw IllegalArgumentException("Unknown condition type")
        }
    }
}

data class MinOrderAmountDto(
    @field:NotNull(message = "Minimum order amount is required")
    @field:DecimalMin(value = "0", message = "Minimum order amount must be at least 0")
    val minAmount: BigDecimal
) : ConditionDto() {
    override fun toEntity(promotionRule: PromotionRule) = MinOrderAmountCondition(
        promotionRule = promotionRule,
        minAmount = minAmount
    )
}

data class ItemCategoryDto(
    @field:Size(min = 1, message = "At least one category ID is required")
    val categoryIds: MutableSet<String>
) : ConditionDto() {
    override fun toEntity(promotionRule: PromotionRule) = ItemCategoryCondition(
        promotionRule = promotionRule,
        categoryIds = categoryIds
    )
}

data class CustomerSegmentDto(
    @field:NotBlank(message = "Customer segment name must not be blank")
    val segmentName: String
) : ConditionDto() {
    override fun toEntity(promotionRule: PromotionRule) = CustomerSegmentCondition(
        promotionRule = promotionRule,
        segmentName = segmentName
    )
}

data class MinItemQuantityDto(
    @field:Min(value = 1, message = "Minimum item quantity must be at least 1")
    val minQuantity: Int
) : ConditionDto() {
    override fun toEntity(promotionRule: PromotionRule) = MinItemQuantityCondition(
        promotionRule = promotionRule,
        minQuantity = minQuantity
    )
}

data class PaymentMethodDto(
    @field:Size(min = 1, message = "At least one payment method is required")
    val paymentMethod: List<String>
) : ConditionDto() {
    override fun toEntity(promotionRule: PromotionRule) = PaymentMethodCondition(
        promotionRule = promotionRule,
        paymentMethod = paymentMethod
    )
}

data class TimeRangeDto(
    val dayOfWeek: DayOfWeek?,

    @field:NotNull(message = "Start time is required")
    val startTime: LocalTime,

    @field:NotNull(message = "End time is required")
    val endTime: LocalTime
) : ConditionDto() {
    override fun toEntity(promotionRule: PromotionRule) = TimeRangeCondition(
        promotionRule = promotionRule,
        dayOfWeek = dayOfWeek,
        startTime = startTime,
        endTime = endTime
    )
}