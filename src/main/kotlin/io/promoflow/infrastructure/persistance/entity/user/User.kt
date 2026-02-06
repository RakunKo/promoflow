package io.promoflow.infrastructure.persistance.entity.user

import io.promoflow.infrastructure.persistance.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Column(nullable = false, length = 25)
    var name: String,

    @Column(nullable = false, length = 100)
    var email: String,

    @Column(nullable = false)
    var providerId: String,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var activeStatus: UserStatus = UserStatus.ACTIVE
): BaseEntity()