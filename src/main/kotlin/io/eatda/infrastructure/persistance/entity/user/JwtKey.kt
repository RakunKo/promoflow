package io.eatda.infrastructure.persistance.entity.user

import io.eatda.infrastructure.persistance.base.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Lob
import jakarta.persistence.Table

@Entity
@Table(name = "jwt_keys")
class JwtKey(
    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    var privateKey: ByteArray,

    @Lob
    @Column(nullable = false, columnDefinition = "LONGBLOB")
    var publicKey: ByteArray,
    var active: Boolean = true
): BaseEntity()