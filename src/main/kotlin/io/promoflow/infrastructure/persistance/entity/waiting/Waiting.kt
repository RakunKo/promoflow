package io.promoflow.infrastructure.persistance.entity.waiting

import io.promoflow.core.common.exception.ApiException
import io.promoflow.core.common.status.WaitingErrorStatus
import io.promoflow.infrastructure.persistance.base.BaseEntity
import io.promoflow.infrastructure.persistance.entity.store.Store
import io.promoflow.infrastructure.persistance.entity.user.User
import jakarta.persistence.*
import java.time.Instant

@Entity
class Waiting(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    val store: Store,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user : User,

    @Column(nullable = false)
    var status: WaitingStatus = WaitingStatus.WAITING,

    @Column
    var enteredAt: Instant? = null,

    @Column
    var canceledAt: Instant? = null,

    @Column
    var noShowAt: Instant? = null,

    @Column(nullable = false)
    val partySize: Int = 1
): BaseEntity() {

    companion object {
        fun create(
            store: Store,
            user: User,
            partySize: Int
        ) = Waiting(
            store = store,
            user = user,
            status = WaitingStatus.WAITING,
            enteredAt = null,
            canceledAt = null,
            noShowAt = null,
            partySize = partySize
        )
    }
    fun enter() {
        if (status != WaitingStatus.WAITING) {
            throw ApiException(WaitingErrorStatus.WAITING_STATUS_IS_NOT_WAITING)
        }
        status = WaitingStatus.ENTERED
        enteredAt = Instant.now()
    }

    fun cancel() {
        if (status != WaitingStatus.WAITING) {
            throw ApiException(WaitingErrorStatus.WAITING_STATUS_IS_NOT_WAITING)
        }
        status = WaitingStatus.CANCELED
        canceledAt = Instant.now()
    }

    fun markNoShow() {
        if (status != WaitingStatus.WAITING) return
        status = WaitingStatus.NO_SHOW
        noShowAt = Instant.now()
    }
}

class InvalidWaitingStateException(message: String) : RuntimeException(message)
