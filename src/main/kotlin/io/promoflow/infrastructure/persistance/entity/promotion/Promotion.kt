package io.promoflow.infrastructure.persistance.entity.promotion

import io.promoflow.api.dto.promotion.request.CreatePromotionRequest
import io.promoflow.infrastructure.persistance.base.BaseEntity
import io.promoflow.infrastructure.persistance.entity.user.User
import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "promotions")
class Promotion(
    @Column(nullable = false, length = 50)
    var name: String,

    @Column(nullable = false)
    var startTime: Instant,

    @Column(nullable = false)
    var endTime: Instant,

    @Enumerated(value = EnumType.STRING)
    var status: PromotionStatus,

    @OneToMany(
        mappedBy = "promotion",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    val rules: MutableList<PromotionRule> = mutableListOf(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user : User
): BaseEntity() {

    companion object {
        fun create(request: CreatePromotionRequest, user: User): Promotion {
            return Promotion(
                name = request.name,
                startTime = request.startTime,
                endTime = request.endTime,
                status = PromotionStatus.DRAFT,
                user = user
            )
        }
    }

    fun updateName(newName: String): Promotion {
        name = newName
        return this
    }

    fun updateDate(newStartTime: Instant, newEndTime: Instant): Promotion {
        startTime = newStartTime
        endTime = newEndTime
        return this
    }

    fun updateStatus(newStatus: PromotionStatus): Promotion {
        status = newStatus
        return this
    }
}