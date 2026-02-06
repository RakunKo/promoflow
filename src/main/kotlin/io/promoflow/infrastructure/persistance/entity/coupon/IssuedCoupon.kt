package io.promoflow.infrastructure.persistance.entity.coupon

import io.promoflow.infrastructure.persistance.base.BaseEntity
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "issued_coupons")
class IssuedCoupon(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    val coupon: Coupon,

    val userId: String,

    var usedAt: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    var status: CouponStatus = CouponStatus.AVAILABLE
): BaseEntity() {

}