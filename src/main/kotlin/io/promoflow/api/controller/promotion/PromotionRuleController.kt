package io.promoflow.api.controller.promotion

import io.promoflow.api.dto.promotion.request.CreatePromotionRuleRequest
import io.promoflow.api.dto.promotion.request.UpdatePromotionNameRequest
import io.promoflow.api.dto.promotion.request.UpdatePromotionRuleRequest
import io.promoflow.api.dto.promotion.response.PromotionIdResponse
import io.promoflow.core.handler.handleApi
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/api/v1/promotion-rules")
class PromotionRuleController(

) {
    @PostMapping
    fun createPromotionRuleApi(
        @Valid @RequestBody body: CreatePromotionRuleRequest,
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.CREATED,
            supplier = { e() },
        )

    @GetMapping("/{promotionRuleId}")
    fun getPromotionRuleApi(
        @PathVariable promotionRuleId: UUID
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.OK,
            supplier = { e() },
        )

    @PatchMapping("/{promotionRuleId}/name")
    fun updatePromotionRuleNameApi(
        @PathVariable promotionRuleId: UUID,
        @Valid @RequestBody body: UpdatePromotionNameRequest
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.OK,
            supplier = { e() },
        )

    @PatchMapping("/{promotionRuleId}/conditions")
    fun updatePromotionRuleConditionApi(
        @PathVariable promotionRuleId: UUID,
        @Valid @RequestBody body: UpdatePromotionRuleRequest
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.OK,
            supplier = { e() },
        )

    @DeleteMapping("/{promotionRuleId}")
    fun deletePromotionApi(
        @PathVariable promotionRuleId: UUID
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.NO_CONTENT,
            supplier = { e() },
        )

    fun e(): PromotionIdResponse {
        return PromotionIdResponse(id = UUID.randomUUID())
    }
}