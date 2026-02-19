package io.eatda.infrastructure.persistance.repository.promotion

import io.eatda.infrastructure.persistance.entity.promotion.effect.PromotionEffect
import io.eatda.infrastructure.persistance.repository.promotion.custom.PromotionEffectDslRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PromotionEffectRepository: JpaRepository<PromotionEffect, UUID>, PromotionEffectDslRepository {
}