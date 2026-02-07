package io.promoflow.core.service.promotion

import io.promoflow.api.dto.promotion.params.PromotionParams
import io.promoflow.api.dto.promotion.request.CreatePromotionRequest
import io.promoflow.api.dto.promotion.request.UpdatePromotionDateRequest
import io.promoflow.api.dto.promotion.request.UpdatePromotionNameRequest
import io.promoflow.core.common.exception.ApiException
import io.promoflow.core.common.status.PromotionErrorStatus
import io.promoflow.core.common.utils.validateStatus
import io.promoflow.core.common.utils.validateStatusNot
import io.promoflow.infrastructure.persistance.base.delete
import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import io.promoflow.infrastructure.persistance.entity.user.User
import io.promoflow.infrastructure.persistance.repository.promotion.PromotionRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.util.UUID

@Service
class PromotionService(
    private val promotionRepository: PromotionRepository
) {
    fun createPromotion(
        request: CreatePromotionRequest,
        user: User
    ): Promotion {
        checkTime(request.startTime, request.endTime)

        return promotionRepository.save(Promotion.create(request, user))
    }

    fun getPromotions(
        params: PromotionParams
    ): List<Promotion> {
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

    fun getPromotionById(
        id: UUID
    ): Promotion = promotionRepository.findById(id)
        .orElseThrow { ApiException(PromotionErrorStatus.PROMOTION_IS_NOT_EXIST) }

    fun updatePromotionName(
        promotion: Promotion,
        request: UpdatePromotionNameRequest,
        user: User
    ): Promotion {
        promotion.checkOwner(user.id)
        return promotion.updateName(newName = request.name)
    }

    fun updatePromotionDate(
        promotion: Promotion,
        request: UpdatePromotionDateRequest,
        user: User
    ): Promotion {
        checkTime(request.startTime, request.endTime)
        promotion.checkOwner(user.id)

        return promotion.updateDate(
            newStartTime = request.startTime,
            newEndTime = request.endTime
        )
    }

    // soft delete
    // 한 서비스는 그 서비스에 대한 책임만 가짐
    fun deletePromotion(
        promotion: Promotion,
        user: User
    ): Promotion {
        promotion.checkOwner(user.id)
        return promotion.delete()
    }

    fun updatePromotionStatus(
        promotion: Promotion,
        status: PromotionStatus,
        user: User
    ): Promotion {
        promotion.checkOwner(user.id)
        promotion.validateStatusNot(
            notAllowStatus = listOf(PromotionStatus.FINISHED, PromotionStatus.DELETED),
            statusSelector = { it.status },
            exception = { ApiException(PromotionErrorStatus.PROMOTION_STATUS_CONFLICT) }
        )

        return promotion.updateStatus(status)
    }

    private fun Promotion.checkOwner(userId: UUID) =
        this.user.id.takeIf { it == userId } ?: throw ApiException(PromotionErrorStatus.PROMOTION_CREATOR_NOT_SAME)

    private fun checkTime(startTime: Instant, endTime: Instant) =
        startTime.takeIf { it.isBefore(endTime) } ?: throw ApiException(PromotionErrorStatus.PROMOTION_DATE_INVALID)
}