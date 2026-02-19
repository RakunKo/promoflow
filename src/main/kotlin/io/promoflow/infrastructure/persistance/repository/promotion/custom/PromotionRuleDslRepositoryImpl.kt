package io.promoflow.infrastructure.persistance.repository.promotion.custom

import com.querydsl.jpa.impl.JPAQueryFactory
import io.promoflow.infrastructure.persistance.entity.promotion.*
import io.promoflow.infrastructure.persistance.repository.dsl.CommonDsl
import io.promoflow.infrastructure.persistance.repository.promotion.custom.PromotionRuleDslRepository
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