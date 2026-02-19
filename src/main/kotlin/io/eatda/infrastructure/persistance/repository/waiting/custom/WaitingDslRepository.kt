package io.eatda.infrastructure.persistance.repository.waiting.custom

import io.eatda.infrastructure.persistance.entity.waiting.Waiting
import java.util.UUID

interface WaitingDslRepository {
    fun findByStoreAndUser(storeId: UUID, userId: UUID): Waiting?
}