package io.eatda.api.dto.promotion.sub

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.eatda.infrastructure.persistance.entity.promotion.PromotionRule
import io.eatda.infrastructure.persistance.entity.promotion.effect.*
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
    JsonSubTypes.Type(value = AmountDiscountDto::class, name = "AMOUNT_DISCOUNT"),
    JsonSubTypes.Type(value = PercentDiscountDto::class, name = "PERCENT_DISCOUNT"),
    JsonSubTypes.Type(value = FreeShippingDto::class, name = "FREE_SHIPPING"),
    JsonSubTypes.Type(value = RewardPointDto::class, name = "REWARD_POINT"),
    JsonSubTypes.Type(value = GiveFreebieDto::class, name = "GIVE_FREEBIE"),
)
abstract class EffectDto {
    abstract fun toEntity(promotionRule: PromotionRule): PromotionEffect

    companion object {
        fun fromEntity(entity: PromotionEffect): EffectDto = when (entity) {
            is AmountDiscountEffect -> AmountDiscountDto(entity.discountAmount)
            is PercentDiscountEffect -> PercentDiscountDto(entity.discountRate, entity.maxDiscountAmount)
            is FreeShippingEffect -> FreeShippingDto()
            is RewardPointEffect -> RewardPointDto(entity.rewardAmount, entity.isPercent)
            is GiveFreebieEffect -> GiveFreebieDto(entity.giftProductId, entity.quantity)
            else -> throw IllegalArgumentException("Unknown effect entity type: ${entity::class.simpleName}")
        }
    }
}

data class AmountDiscountDto(
    @field:NotNull(message = "Discount amount is required")
    @field:DecimalMin(value = "100", message = "Discount amount must be at least 100")
    val discountAmount: BigDecimal
) : EffectDto() {
    override fun toEntity(promotionRule: PromotionRule) = AmountDiscountEffect(
        promotionRule = promotionRule,
        discountAmount = discountAmount
    )
}

data class PercentDiscountDto(
    @field:NotNull(message = "Discount rate is required")
    @field:DecimalMin(value = "1", message = "Discount rate must be at least 1%")
    @field:DecimalMax(value = "100", message = "Discount rate cannot exceed 100%")
    val discountRate: Double,
    val maxDiscountAmount: BigDecimal?
) : EffectDto() {
    override fun toEntity(promotionRule: PromotionRule) = PercentDiscountEffect(
        promotionRule = promotionRule,
        discountRate = discountRate,
        maxDiscountAmount = maxDiscountAmount
    )
}

class FreeShippingDto : EffectDto() {
    override fun toEntity(promotionRule: PromotionRule) = FreeShippingEffect(
        promotionRule = promotionRule
    )
}

data class RewardPointDto(
    @field:NotNull(message = "Reward amount is required")
    val rewardAmount: BigDecimal,
    val isPercent: Boolean = false
) : EffectDto() {
    override fun toEntity(promotionRule: PromotionRule) = RewardPointEffect(
        promotionRule = promotionRule,
        rewardAmount = rewardAmount,
        isPercent = isPercent
    )
}

data class GiveFreebieDto(
    @field:NotNull(message = "Gift product ID is required")
    val giftProductId: Long,
    @field:Min(value = 1, message = "Gift quantity must be at least 1")
    val quantity: Int = 1
) : EffectDto() {
    override fun toEntity(promotionRule: PromotionRule) = GiveFreebieEffect(
        promotionRule = promotionRule,
        giftProductId = giftProductId,
        quantity = quantity
    )
}