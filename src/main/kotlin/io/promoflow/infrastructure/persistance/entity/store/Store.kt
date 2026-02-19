package io.promoflow.infrastructure.persistance.entity.store

import io.promoflow.infrastructure.persistance.base.BaseEntity
import io.promoflow.infrastructure.persistance.entity.user.User
import jakarta.persistence.*
import java.time.Instant

@Entity
class Store(
    @Column(nullable = false, length = 100)
    var name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val owner : User,

    @Column(nullable = false)
    var maxWaitingSize: Int = 50,

    @Column(nullable = false)
    var isOpen: Boolean = true,
): BaseEntity() {

    fun open() {
        isOpen = true
        updatedAt = Instant.now()
    }

    fun close() {
        isOpen = false
        updatedAt = Instant.now()
    }

    fun updateMaxWaitingSize(size: Int) {
        require(size > 0) { "최대 대기 인원은 1 이상이어야 합니다." }
        maxWaitingSize = size
        updatedAt = Instant.now()
    }
}
