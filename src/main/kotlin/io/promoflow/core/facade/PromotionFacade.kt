package io.promoflow.core.facade

import io.promoflow.api.dto.promotion.params.PromotionParams
import io.promoflow.api.dto.promotion.request.CreatePromotionRequest
import io.promoflow.api.dto.promotion.request.UpdatePromotionDateRequest
import io.promoflow.api.dto.promotion.request.UpdatePromotionNameRequest
import io.promoflow.api.dto.promotion.response.GetPromotionResponse
import io.promoflow.api.dto.promotion.response.GetPromotionsResponse
import io.promoflow.api.dto.promotion.response.PromotionIdResponse
import io.promoflow.core.service.promotion.PromotionConditionService
import io.promoflow.core.service.promotion.PromotionEffectService
import io.promoflow.core.service.promotion.PromotionRuleService
import io.promoflow.core.service.promotion.PromotionService
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import io.promoflow.infrastructure.persistance.entity.user.User
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Component
class PromotionFacade(
    private val promotionService: PromotionService,
    private val promotionRuleService: PromotionRuleService,
    private val promotionConditionService: PromotionConditionService,
    private val promotionEffectService: PromotionEffectService,
) {
    @Transactional
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

    @Transactional
    fun modifyPromotionName(
        id: UUID,
        request: UpdatePromotionNameRequest,
        user: User
    ): PromotionIdResponse {
        val promotion = promotionService.updatePromotionName(
            promotionService.getPromotionById(id),
            request,
            user
        )

        return PromotionIdResponse.form(promotion)
    }

    @Transactional
    fun modifyPromotionDate(
        id: UUID,
        request: UpdatePromotionDateRequest,
        user: User
    ): PromotionIdResponse {
        val promotion = promotionService.updatePromotionDate(
            promotionService.getPromotionById(id),
            request,
            user
        )

        return PromotionIdResponse.form(promotion)
    }

    @Transactional
    fun deletePromotion(
        id: UUID,
        user: User
    ): PromotionIdResponse {
        val promotion = promotionService.getPromotionById(id)
        val rules = promotion.rules

        val ruleIds = rules.map { it.id }
        val conditionIds = rules.flatMap { rule -> rule.conditions.map { it.id } }
        val effectIds = rules.flatMap { rule -> rule.effects.map { it.id } }

        if (conditionIds.isNotEmpty()) promotionConditionService.deletePromotionConditions(conditionIds)
        if (effectIds.isNotEmpty())promotionEffectService.deletePromotionEffects(effectIds)
        if (rules.isNotEmpty())promotionRuleService.deletePromotionRules(ruleIds)

        val deletedPromotion = promotionService.deletePromotion(promotion, user)

        return PromotionIdResponse.form(deletedPromotion)
    }

    @Transactional
    fun modifyPromotionStatus(
        id: UUID,
        status: PromotionStatus,
        user: User
    ): PromotionIdResponse {
        val promotion = promotionService.updatePromotionStatus(
            promotionService.getPromotionById(id),
            status,
            user
        )

        return PromotionIdResponse.form(promotion)
    }
}