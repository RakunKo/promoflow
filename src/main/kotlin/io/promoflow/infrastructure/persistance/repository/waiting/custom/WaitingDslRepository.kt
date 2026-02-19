package io.promoflow.infrastructure.persistance.repository.waiting.custom

import io.promoflow.infrastructure.persistance.entity.waiting.Waiting
import java.util.UUID

interface WaitingDslRepository {
    fun findByStoreAndUser(storeId: UUID, userId: UUID): Waiting?
}