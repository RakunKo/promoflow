package io.promoflow.core.service.promotion

import io.promoflow.api.dto.promotion.request.CreatePromotionRuleRequest
import io.promoflow.core.common.exception.ApiException
import io.promoflow.core.common.status.PromotionErrorStatus
import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import io.promoflow.infrastructure.persistance.repository.promotion.PromotionRuleRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class PromotionRuleService(
    private val promotionRuleRepository: PromotionRuleRepository
) {
    fun createPromotionRule(
        request: CreatePromotionRuleRequest,
        promotion: Promotion
    ): PromotionRule {
        if(getPromotionRulesByPromotion(promotion).size > 5)
            throw ApiException(PromotionErrorStatus.PROMOTION_RULE_IS_EXCEEDED)

        return promotionRuleRepository.save(PromotionRule.create(request, promotion))
    }

    fun getPromotionRuleById(
        id: UUID
    ): PromotionRule = promotionRuleRepository.findById(id)
        .orElseThrow { ApiException(PromotionErrorStatus.PROMOTION_RULE_IS_NOT_EXIST, id) }

    fun getPromotionRulesByPromotion(
        promotion: Promotion
    ): List<PromotionRule> = promotionRuleRepository.findPromotionRulesByPromotionId(promotion.id)


    // soft delete, bulk delete
    fun deletePromotionRules(
        promotionRuleIds: List<UUID>,
    ) {
        promotionRuleRepository.deletePromotionRules(
            promotionRuleIds = promotionRuleIds
        )
    }

}