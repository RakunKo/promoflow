package io.promoflow.infrastructure.persistance.entity.user

enum class UserStatus(
    val value: String,
) {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    DELETED("DELETED"),
    ;

    companion object {
        fun from(value: String): UserStatus =
            entries.firstOrNull { it.value == value }
                ?: throw IllegalArgumentException("Unknown status: $value")
    }
}
