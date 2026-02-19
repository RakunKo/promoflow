package io.eatda.infrastructure.persistance.base

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
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
    open var createdAt: Instant = Instant.now()
        protected set

    @LastModifiedDate
    open var updatedAt: Instant = Instant.now()
        protected set

    open var deletedAt: Instant? = null
        protected set

    val isDeleted: Boolean
        get() = deletedAt != null

    fun markAsDeleted() {
        this.deletedAt = Instant.now()
    }

    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is BaseEntity) return false

        return id == other.id
    }
    override fun hashCode(): Int = id.hashCode() ?: 0
}

fun <T : BaseEntity> T.delete(): T = apply { markAsDeleted() }