package io.promoflow.infrastructure.persistance.repository.promotion

import io.promoflow.infrastructure.persistance.entity.promotion.condition.PromotionCondition
import io.promoflow.infrastructure.persistance.repository.promotion.custom.PromotionConditionDslRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PromotionConditionRepository: JpaRepository<PromotionCondition, UUID>, PromotionConditionDslRepository {
}