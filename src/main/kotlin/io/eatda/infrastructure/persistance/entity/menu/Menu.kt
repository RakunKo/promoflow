package io.eatda.infrastructure.persistance.entity.menu

import io.eatda.infrastructure.persistance.base.BaseEntity
import io.eatda.infrastructure.persistance.entity.store.Store
import jakarta.persistence.*
import java.time.Instant

@Entity
class Menu(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    val store: Store,

    @Column(nullable = false, length = 100)
    val name: String,

    @Column(length = 500)
    val description: String? = null,

    @Column(length = 200)
    val imageUrl: String? = null,

    @Column(nullable = false)
    val price: Long,

    @OneToMany(mappedBy = "menu", cascade = [CascadeType.ALL], orphanRemoval = true)
    val options: MutableList<MenuOption> = mutableListOf(),
): BaseEntity() {
    fun addOption(option: MenuOption) {
        option.menu = this
        options.add(option)
        updatedAt = Instant.now()
    }

    fun removeOption(option: MenuOption) {
        options.remove(option)
        option.menu = null
        updatedAt = Instant.now()
    }
}
