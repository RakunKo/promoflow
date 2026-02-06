package io.promoflow.core.service

import io.promoflow.api.dto.promotion.params.PromotionParams
import io.promoflow.api.dto.promotion.request.CreatePromotionRequest
import io.promoflow.core.common.exception.ApiException
import io.promoflow.core.common.status.PromotionErrorStatus
import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.user.User
import io.promoflow.infrastructure.persistance.repository.PromotionRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PromotionService(
    private val promotionRepository: PromotionRepository
) {
    fun createPromotion(request: CreatePromotionRequest, user: User): Promotion {
        if(request.startTime >= request.endTime) throw ApiException(PromotionErrorStatus.PROMOTION_DATE_INVALID)

        return promotionRepository.save(Promotion.create(request, user))
    }

    fun getPromotions(params: PromotionParams): List<Promotion> {
        if(params.startTime != null && params.endTime != null && params.startTime > params.endTime)
            throw ApiException(PromotionErrorStatus.PROMOTION_DATE_INVALID)

        return promotionRepository.searchPromotion(
            status = params.status,
            startTime = params.startTime,
            endTime = params.endTime,
            page = params.page,
            size = params.size
        )
    }

    fun getPromotionById(id: UUID): Promotion = promotionRepository.findById(id)
        .orElseThrow { ApiException(PromotionErrorStatus.PROMOTION_IS_NOT_EXIST) }
}