package io.eatda.infrastructure.persistance.repository.waiting.custom

import io.eatda.core.common.exception.ApiException
import io.eatda.core.common.exception.InfraException
import io.eatda.core.common.status.WaitingErrorStatus
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class WaitingRedisRepositoryImpl(
    private val redisTemplate: RedisTemplate<String, String>
): WaitingRedisRepository {
    override fun add(storeId: UUID, userId: UUID): Boolean =
        redisTemplate.opsForZSet().addIfAbsent(
            storeId.getKey(),
            userId.toString(),
            System.currentTimeMillis().toDouble()
        ) ?: false

    override fun getRank(storeId: UUID, userId: UUID): Long =
        redisTemplate.opsForZSet().rank(
            storeId.getKey(),
            userId.toString()
        )?.plus(1)
            ?: throw InfraException(WaitingErrorStatus.NO_MORE_WAITING)

    override fun popNext(storeId: UUID): UUID =
        UUID.fromString(
            redisTemplate.opsForZSet().popMin(storeId.getKey())
                ?.value
                ?: throw ApiException(WaitingErrorStatus.NO_MORE_WAITING)
        )

    override fun remove(storeId: UUID, userId: UUID): Long =
        redisTemplate.opsForZSet().remove(
            storeId.getKey(),
            userId.toString()
        ) ?: throw InfraException(WaitingErrorStatus.NO_MORE_WAITING)

    override fun count(storeId: UUID): Long = redisTemplate.opsForZSet().zCard(storeId.getKey()) ?: 0

    private fun UUID.getKey() = "waiting:$this"
}