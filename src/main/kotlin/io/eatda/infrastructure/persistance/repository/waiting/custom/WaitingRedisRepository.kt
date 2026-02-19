package io.eatda.infrastructure.persistance.repository.waiting.custom

import java.util.UUID

interface WaitingRedisRepository {
    fun add(storeId: UUID, userId: UUID): Boolean
    fun getRank(storeId: UUID, userId: UUID): Long
    fun popNext(storeId: UUID): UUID
    fun remove(storeId: UUID, userId: UUID): Long
    fun count(storeId: UUID): Long
}