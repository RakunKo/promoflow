package io.promoflow.core.facade

import io.promoflow.api.dto.promotion.params.PromotionParams
import io.promoflow.api.dto.promotion.request.CreatePromotionRequest
import io.promoflow.api.dto.promotion.response.GetPromotionResponse
import io.promoflow.api.dto.promotion.response.GetPromotionsResponse
import io.promoflow.api.dto.promotion.response.PromotionIdResponse
import io.promoflow.core.service.PromotionService
import io.promoflow.infrastructure.persistance.entity.user.User
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class PromotionFacade(
    private val promotionService: PromotionService
) {
    fun registerPromotion(request: CreatePromotionRequest, user: User): PromotionIdResponse {
        val promotion = promotionService.createPromotion(request, user)
        return PromotionIdResponse.form(promotion)
    }

    fun getPromotions(params: PromotionParams): GetPromotionsResponse {
        val promotions = promotionService.getPromotions(params)
        return GetPromotionsResponse.from(promotions)
    }

    fun getPromotionDetail(id: UUID): GetPromotionResponse {
        val promotion = promotionService.getPromotionById(id)
        return GetPromotionResponse.from(promotion)
    }
}