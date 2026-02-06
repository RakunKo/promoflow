package io.promoflow.api.controller.promotion

import io.promoflow.api.dto.promotion.params.PromotionParams
import io.promoflow.api.dto.promotion.request.CreatePromotionRequest
import io.promoflow.api.dto.promotion.response.PromotionIdResponse
import io.promoflow.api.dto.promotion.request.UpdatePromotionDateRequest
import io.promoflow.api.dto.promotion.request.UpdatePromotionNameRequest
import io.promoflow.api.dto.promotion.response.GetPromotionResponse
import io.promoflow.api.dto.promotion.response.GetPromotionsResponse
import io.promoflow.core.facade.PromotionFacade
import io.promoflow.core.handler.handleApi
import io.promoflow.infrastructure.persistance.entity.user.User
import io.promoflow.infrastructure.security.annotations.AuthUser
import jakarta.validation.Valid
import org.springdoc.core.annotations.ParameterObject
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
@RequestMapping("/api/v1/promotions")
class PromotionController(
    private val promotionFacade: PromotionFacade
) {
    @PostMapping
    fun createPromotionApi(
        @Valid @RequestBody body: CreatePromotionRequest,
        @AuthUser user: User
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.CREATED,
            supplier = { promotionFacade.registerPromotion(body, user) },
        )

    @GetMapping
    fun getPromotionsApi(
        @Valid @ParameterObject params: PromotionParams
    ): ResponseEntity<GetPromotionsResponse> =
        handleApi(
            status = HttpStatus.OK,
            supplier = { promotionFacade.getPromotions(params) },
        )

    @GetMapping("/{promotionId}")
    fun getPromotionApi(
        @PathVariable promotionId: UUID
    ): ResponseEntity<GetPromotionResponse> =
        handleApi(
            status = HttpStatus.OK,
            supplier = { promotionFacade.getPromotionDetail(promotionId) },
        )

    @PatchMapping("/{promotionId}/name")
    fun updatePromotionNameApi(
        @PathVariable promotionId: UUID,
        @Valid @RequestBody body: UpdatePromotionNameRequest
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.OK,
            supplier = { e() },
        )

    @PatchMapping("/{promotionId}/date")
    fun updatePromotionDateApi(
        @PathVariable promotionId: UUID,
        @Valid @RequestBody body: UpdatePromotionDateRequest
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.OK,
            supplier = { e() },
        )

    @DeleteMapping("/{promotionId}")
    fun deletePromotionApi(
        @PathVariable promotionId: UUID
    ): ResponseEntity<PromotionIdResponse> =
        handleApi(
            status = HttpStatus.NO_CONTENT,
            supplier = { e() },
        )

    fun e(): PromotionIdResponse {
        return PromotionIdResponse(id = UUID.randomUUID())
    }
}