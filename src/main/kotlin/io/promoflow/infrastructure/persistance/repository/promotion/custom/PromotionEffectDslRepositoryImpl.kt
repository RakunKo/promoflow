package io.promoflow.infrastructure.persistance.repository.promotion.custom

import com.querydsl.jpa.impl.JPAQueryFactory
import io.promoflow.infrastructure.persistance.entity.promotion.effect.QPromotionEffect
import io.promoflow.infrastructure.persistance.repository.dsl.CommonDsl
import io.promoflow.infrastructure.persistance.repository.promotion.custom.PromotionEffectDslRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.UUID

@Repository
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