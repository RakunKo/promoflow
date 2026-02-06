package io.promoflow.api.dto.promotion.sub

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.validation.constraints.DecimalMax
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "effectType"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = AmountDiscountRequest::class, name = "AMOUNT_DISCOUNT"),
    JsonSubTypes.Type(value = PercentDiscountRequest::class, name = "PERCENT_DISCOUNT"),
    JsonSubTypes.Type(value = FreeShippingRequest::class, name = "FREE_SHIPPING"),
    JsonSubTypes.Type(value = RewardPointRequest::class, name = "REWARD_POINT"),
    JsonSubTypes.Type(value = GiveFreebieRequest::class, name = "GIVE_FREEBIE"),
)
abstract class EffectRequest

data class AmountDiscountRequest(
    @field:NotNull(message = "Discount amount is required")
    @field:DecimalMin(value = "100", message = "Discount amount must be at least 100")
    val discountAmount: BigDecimal
) : EffectRequest()

data class PercentDiscountRequest(
    @field:NotNull(message = "Discount rate is required")
    @field:DecimalMin(value = "1", message = "Discount rate must be at least 1%")
    @field:DecimalMax(value = "100", message = "Discount rate cannot exceed 100%")
    val discountRate: Double,
    val maxDiscountAmount: BigDecimal?
) : EffectRequest()

class FreeShippingRequest : EffectRequest()

data class RewardPointRequest(
    @field:NotNull(message = "Reward amount is required")
    val rewardAmount: BigDecimal,
    val isPercent: Boolean = false
) : EffectRequest()

data class GiveFreebieRequest(
    @field:NotNull(message = "Gift product ID is required")
    val giftProductId: Long,
    @field:Min(value = 1, message = "Gift quantity must be at least 1")
    val quantity: Int = 1
) : EffectRequest()