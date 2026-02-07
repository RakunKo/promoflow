package io.promoflow.infrastructure.persistance.repository.dsl.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import io.promoflow.infrastructure.persistance.entity.promotion.condition.QPromotionCondition
import io.promoflow.infrastructure.persistance.repository.dsl.CommonDsl
import io.promoflow.infrastructure.persistance.repository.dsl.PromotionConditionDslRepository
import java.time.Instant
import java.util.UUID

class PromotionConditionDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): PromotionConditionDslRepository, CommonDsl() {

    private val promotionCondition = QPromotionCondition.promotionCondition

    override fun deletePromotionConditions(promotionConditionIds: List<UUID>): Long {
        val now = Instant.now()

        return queryFactory.update(promotionCondition)
            .set(promotionCondition.deletedAt, now)
            .where(promotionCondition.id.`in`(promotionConditionIds))
            .execute()
    }


}