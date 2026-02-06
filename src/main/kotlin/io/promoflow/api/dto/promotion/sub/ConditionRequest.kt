package io.promoflow.api.dto.promotion.sub

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import jakarta.validation.constraints.*
import java.math.BigDecimal
import java.time.DayOfWeek
import java.time.LocalDateTime

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "conditionType"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = MinOrderAmountRequest::class, name = "MIN_ORDER_AMOUNT"),
    JsonSubTypes.Type(value = ItemCategoryRequest::class, name = "ITEM_CATEGORY"),
    JsonSubTypes.Type(value = CustomerSegmentRequest::class, name = "CUSTOMER_SEGMENT"),
    JsonSubTypes.Type(value = MinItemQuantityRequest::class, name = "MIN_ITEM_QUANTITY"),
    JsonSubTypes.Type(value = PaymentMethodRequest::class, name = "PAYMENT_METHOD"),
    JsonSubTypes.Type(value = TimeRangeRequest::class, name = "TIME_RANGE"),
)
abstract class ConditionRequest

data class MinOrderAmountRequest(
    @field:NotNull(message = "Minimum order amount is required")
    @field:DecimalMin(value = "0", message = "Minimum order amount must be at least 0")
    val minAmount: BigDecimal
) : ConditionRequest()

data class ItemCategoryRequest(
    @field:Size(min = 1, message = "At least one category ID is required")
    val categoryIds: List<String>
) : ConditionRequest()

data class CustomerSegmentRequest(
    @field:NotBlank(message = "Customer segment name must not be blank")
    val segmentName: String
) : ConditionRequest()

data class MinItemQuantityRequest(
    @field:Min(value = 1, message = "Minimum item quantity must be at least 1")
    val minQuantity: Int
) : ConditionRequest()

data class PaymentMethodRequest(
    @field:Size(min = 1, message = "At least one payment method is required")
    val paymentMethod: List<String>
) : ConditionRequest()

data class TimeRangeRequest(
    val dayOfWeek: DayOfWeek?,

    @field:NotNull(message = "Start time is required")
    val startTime: LocalDateTime,

    @field:NotNull(message = "End time is required")
    val endTime: LocalDateTime
) : ConditionRequest()