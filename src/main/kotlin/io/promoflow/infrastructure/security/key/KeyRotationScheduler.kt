package nexio.apiserver.infrastructure.security.key

import io.promoflow.infrastructure.security.key.KeyManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class KeyRotationScheduler(
    private val keyManager: KeyManager,
) {
    @Scheduled(cron = "0 0 0 */30 * ?")
    fun rotateKeyScheduled() {
        keyManager.rotateKey()
        keyManager.cleanupOldKeys()
        println("[JWT] 🔄 RSA key rotation completed")
    }
}
