package io.promoflow.infrastructure.persistance.base

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity(
) {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "BINARY(16)")
    open lateinit var id: UUID
        protected set

    @CreatedDate
    open var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    open var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is BaseEntity) return false

        return id == other.id
    }
    override fun hashCode(): Int = id.hashCode() ?: 0
}