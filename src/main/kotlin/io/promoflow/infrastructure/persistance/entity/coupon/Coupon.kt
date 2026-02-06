package io.promoflow.infrastructure.persistance.entity.coupon

import io.promoflow.infrastructure.persistance.base.BaseEntity
import io.promoflow.infrastructure.persistance.entity.promotion.PromotionRule
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "coupons")
class Coupon(
    @Column(nullable = false, length = 50)
    val name: String,

    @Column(nullable = true, length = 20)
    val code: String?,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_rule_id")
    val rule: PromotionRule,

    val validFrom: LocalDateTime,
    val validTo: LocalDateTime,

    // if null -> infinite
    val totalQuantity: Int?
): BaseEntity() {

}