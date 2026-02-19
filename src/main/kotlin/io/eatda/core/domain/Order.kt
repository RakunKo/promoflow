package io.eatda.core.domain

import java.math.BigDecimal
import java.time.LocalDateTime

data class Order(
    val orderId: String,
    val customer: CustomerInfo,
    val items: List<OrderItem>,
    val paymentMethod: String,
    val orderedAt: LocalDateTime = LocalDateTime.now(),
    val deliveryFee: BigDecimal = BigDecimal("3000")
) {
    val totalAmount: BigDecimal get() = items.sumOf { it.totalPrice }

    fun hasCategory(categoryIds: List<String>): Boolean {
        return items.any { it.categoryId in categoryIds }
    }

    val totalQuantity: Int get() = items.sumOf { it.quantity }
}

data class OrderItem(
    val productId: String,
    val productName: String,
    val categoryId: String,
    val price: BigDecimal,
    val quantity: Int
) {
    val totalPrice: BigDecimal get() = price.multiply(BigDecimal(quantity))
}

data class CustomerInfo(
    val id: String,
    val segment: String,
    val isFirstOrder: Boolean
)