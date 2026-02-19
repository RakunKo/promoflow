package io.promoflow.infrastructure.persistance.entity.menu

import io.promoflow.infrastructure.persistance.base.BaseEntity
import jakarta.persistence.*

@Entity
class MenuOption(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    var menu: Menu? = null,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(length = 500)
    val description: String? = null,

    @Column(nullable = false)
    val additionalPrice: Long = 0L,
): BaseEntity()
