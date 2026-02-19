package io.promoflow.core.facade

import io.promoflow.api.dto.common.IdResponse
import io.promoflow.api.dto.waiting.request.CreateWaitingRequest
import io.promoflow.api.dto.waiting.response.GetWaitingStatusResponse
import io.promoflow.core.service.store.StoreService
import io.promoflow.core.service.waiting.WaitingService
import io.promoflow.infrastructure.persistance.entity.user.User
import jakarta.transaction.Transactional
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class WaitingFacade(
    private val waitingService: WaitingService,
    private val storeService: StoreService
) {
    @Transactional
    fun register(
        request: CreateWaitingRequest,
        id: UUID,
        user: User
    ): IdResponse {
        val store = storeService.getStoreById(id)

        user.isConsumer()

        val waiting = waitingService.registerWaiting(
            store, user, request.partySize
        )

        return IdResponse.of(waiting.id)
    }

    fun getWaitingStatus(
        id: UUID,
        user: User
    ): GetWaitingStatusResponse {
        val store = storeService.getStoreById(id)
        val waiting = waitingService.getWaitingByUser(store.id, user.id)

        val myRank = waitingService.getWaitingRank(store, user)
        val totalCount = waitingService.getWaitingCount(store)

        return GetWaitingStatusResponse.of(store, waiting, myRank, totalCount)
    }


}