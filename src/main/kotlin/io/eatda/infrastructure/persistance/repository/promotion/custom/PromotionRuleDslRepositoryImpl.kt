package io.eatda.infrastructure.persistance.repository.promotion.custom

import com.querydsl.jpa.impl.JPAQueryFactory
import io.eatda.infrastructure.persistance.entity.promotion.*
import io.eatda.infrastructure.persistance.repository.dsl.CommonDsl
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.UUID


@Repository
class PromotionRuleDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): PromotionRuleDslRepository, CommonDsl() {

    private val promotionRule = QPromotionRule.promotionRule
    override fun deletePromotionRules(promotionRuleIds: List<UUID>): Long {
        val now = Instant.now()

        return queryFactory.update(promotionRule)
            .set(promotionRule.deletedAt, now)
            .where(promotionRule.id.`in`(promotionRuleIds))
            .execute()
    }


}