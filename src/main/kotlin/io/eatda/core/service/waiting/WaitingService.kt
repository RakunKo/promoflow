package io.eatda.core.service.waiting

import io.eatda.core.common.exception.ApiException
import io.eatda.core.common.status.WaitingErrorStatus
import io.eatda.infrastructure.persistance.entity.store.Store
import io.eatda.infrastructure.persistance.entity.user.User
import io.eatda.infrastructure.persistance.entity.waiting.Waiting
import io.eatda.infrastructure.persistance.repository.waiting.WaitingRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class WaitingService(
    private val waitingRepository: WaitingRepository
) {
    fun registerWaiting(
        store: Store,
        user: User,
        partySize: Int
    ): Waiting {
        val waiting = Waiting.create(
            store = store,
            user = user,
            partySize = partySize
        )
        val saved = waitingRepository.save(waiting)

        try {
            val added = waitingRepository.add(store.id, user.id)
            if(!added) throw ApiException(WaitingErrorStatus.WAITING_IS_ALREADY_EXISTED)
        } catch (e: Exception){
            waitingRepository.remove(store.id, user.id)
            throw e
        }

        return saved
    }

    fun getWaitingRank(store: Store, user: User) = waitingRepository.getRank(store.id, user.id)
    fun getWaitingCount(store: Store) = waitingRepository.count(store.id)

    fun cancelWaiting(
        store: Store,
        user: User,
    ): Waiting {
        val waiting = getWaitingByUser(store.id, user.id)

        waitingRepository.remove(store.id, user.id)
        waiting.cancel()

        return waiting
    }

    fun callWaiting(
        store: Store
    ): Waiting {
        val userId = waitingRepository.popNext(store.id)
        val waiting = getWaitingByUser(store.id, userId)

        waiting.enter()

        return waiting
    }

    fun getWaitingByUser(
        storeId: UUID, userId: UUID
    ): Waiting = waitingRepository.findWaitingByStoreIdAndUserId(storeId, userId)
        ?: throw ApiException(WaitingErrorStatus.WAITING_IS_NOT_EXIST)
}