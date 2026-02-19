package io.promoflow.infrastructure.persistance.repository.promotion.custom

import com.querydsl.jpa.impl.JPAQueryFactory
import io.promoflow.infrastructure.persistance.entity.promotion.condition.QPromotionCondition
import io.promoflow.infrastructure.persistance.repository.dsl.CommonDsl
import io.promoflow.infrastructure.persistance.repository.promotion.custom.PromotionConditionDslRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.UUID

@Repository
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