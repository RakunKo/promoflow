package io.eatda.infrastructure.persistance.repository.waiting.custom

import com.querydsl.jpa.impl.JPAQueryFactory
import io.eatda.infrastructure.persistance.entity.waiting.QWaiting
import io.eatda.infrastructure.persistance.entity.waiting.Waiting
import io.eatda.infrastructure.persistance.repository.dsl.CommonDsl
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class WaitingDslRepositoryImpl(
    private val queryFactory: JPAQueryFactory,
): WaitingDslRepository, CommonDsl() {

    private val waiting = QWaiting.waiting
    override fun findByStoreAndUser(storeId: UUID, userId: UUID): Waiting? {
        return queryFactory.selectFrom(waiting)
            .where(
                eq(waiting.store.id, storeId),
                eq(waiting.user.id, userId)
            )
            .fetchOne()
    }
}