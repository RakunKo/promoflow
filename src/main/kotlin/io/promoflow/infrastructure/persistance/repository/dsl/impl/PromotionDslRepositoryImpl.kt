package io.promoflow.infrastructure.persistance.repository.dsl.impl

import com.querydsl.jpa.impl.JPAQueryFactory
import io.promoflow.infrastructure.persistance.entity.promotion.Promotion
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionStatus
import io.promoflow.infrastructure.persistance.entity.promotion.QPromotion
import io.promoflow.infrastructure.persistance.entity.user.QUser
import io.promoflow.infrastructure.persistance.repository.dsl.CommonDsl
import io.promoflow.infrastructure.persistance.repository.dsl.PromotionDslRepository
import java.time.LocalDateTime


class PromotionDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): PromotionDslRepository, CommonDsl() {

    private val promotion = QPromotion.promotion
    private val user = QUser.user

    override fun searchPromotion(
        status: PromotionStatus?,
        startTime: LocalDateTime?,
        endTime: LocalDateTime?,
        page: Int,
        size: Int
    ): List<Promotion> {
        return queryFactory.selectFrom(promotion)
            .leftJoin(promotion.user, user).fetchJoin()
            .where(
                eq(promotion.status, status),
                between(promotion.startTime, startTime, endTime)
            )
            .orderBy(promotion.createdAt.desc())
            .pagination(page, size)
            .fetch()
    }
}