package io.eatda.core.service.promotion

import io.eatda.api.dto.promotion.params.PromotionParams
import io.eatda.api.dto.promotion.request.CreatePromotionRequest
import io.eatda.api.dto.promotion.request.UpdatePromotionDateRequest
import io.eatda.api.dto.promotion.request.UpdatePromotionNameRequest
import io.eatda.core.common.exception.ApiException
import io.eatda.core.common.status.PromotionErrorStatus
import io.eatda.core.common.utils.validateStatusNot
import io.eatda.infrastructure.persistance.base.delete
import io.eatda.infrastructure.persistance.entity.promotion.Promotion
import io.eatda.infrastructure.persistance.entity.promotion.PromotionStatus
import io.eatda.infrastructure.persistance.entity.user.User
import io.eatda.infrastructure.persistance.repository.promotion.PromotionRepository
import org.springframework.stereotype.Service
import java.time.Instant
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
        .orElseThrow { ApiException(PromotionErrorStatus.PROMOTION_IS_NOT_EXIST, id) }

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