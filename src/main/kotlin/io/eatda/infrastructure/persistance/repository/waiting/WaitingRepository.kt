package io.eatda.infrastructure.persistance.repository.waiting

import io.eatda.infrastructure.persistance.entity.waiting.Waiting
import io.eatda.infrastructure.persistance.repository.waiting.custom.WaitingDslRepository
import io.eatda.infrastructure.persistance.repository.waiting.custom.WaitingRedisRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface WaitingRepository: JpaRepository<Waiting, UUID>, WaitingRedisRepository, WaitingDslRepository {
    fun findWaitingByStoreIdAndUserId(storeId: UUID, userId: UUID): Waiting?
}