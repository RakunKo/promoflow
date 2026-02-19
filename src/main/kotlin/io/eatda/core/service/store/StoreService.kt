package io.eatda.core.service.store

import io.eatda.core.common.exception.ApiException
import io.eatda.core.common.status.StoreErrorStatus
import io.eatda.infrastructure.persistance.entity.store.Store
import io.eatda.infrastructure.persistance.repository.store.StoreRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class StoreService(
    private val storeRepository: StoreRepository
) {
    fun getStoreById(id: UUID): Store =  storeRepository.findByIdOrNull(id)
        ?: throw ApiException(StoreErrorStatus.STORE_IS_NOT_EXIST)
}