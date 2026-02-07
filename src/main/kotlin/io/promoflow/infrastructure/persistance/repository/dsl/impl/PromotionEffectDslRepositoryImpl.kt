package io.promoflow.infrastructure.persistance.repository.dsl.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import io.promoflow.infrastructure.persistance.entity.promotion.effect.QPromotionEffect
import io.promoflow.infrastructure.persistance.repository.dsl.CommonDsl
import io.promoflow.infrastructure.persistance.repository.dsl.PromotionEffectDslRepository
import java.time.Instant
import java.util.UUID


class PromotionEffectDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): PromotionEffectDslRepository, CommonDsl() {

    private val promotionEffect = QPromotionEffect.promotionEffect
    override fun deletePromotionEffects(promotionEffectIds: List<UUID>): Long {
        val now = Instant.now()

        return queryFactory.update(promotionEffect)
            .set(promotionEffect.deletedAt, now)
            .where(promotionEffect.id.`in`(promotionEffectIds))
            .execute()
    }
}