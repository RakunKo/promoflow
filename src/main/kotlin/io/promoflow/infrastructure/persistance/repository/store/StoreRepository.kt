package io.promoflow.infrastructure.persistance.repository.store

import io.promoflow.infrastructure.persistance.entity.store.Store
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface StoreRepository : JpaRepository<Store, UUID> {
}
