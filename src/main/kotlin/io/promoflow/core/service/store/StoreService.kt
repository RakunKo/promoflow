package io.promoflow.core.service.store

import io.promoflow.core.common.exception.ApiException
import io.promoflow.core.common.status.StoreErrorStatus
import io.promoflow.infrastructure.persistance.entity.store.Store
import io.promoflow.infrastructure.persistance.repository.store.StoreRepository
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