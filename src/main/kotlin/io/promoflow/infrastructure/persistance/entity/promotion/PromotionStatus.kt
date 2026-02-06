package io.promoflow.infrastructure.persistance.entity.promotion

enum class PromotionStatus() {
    DRAFT,
    PENDING,
    READY,
    ACTIVE,
    PAUSED,
    FINISHED,
    DELETED
}