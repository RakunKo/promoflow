package io.eatda.core.service

import io.eatda.core.common.exception.ApiException
import io.eatda.core.common.status.UserErrorStatus
import io.eatda.api.dto.user.UpdateUsernameRequest
import io.eatda.api.dto.user.SignupUserRequest
import io.eatda.api.dto.user.UserIdResponse
import io.eatda.infrastructure.persistance.entity.user.User
import io.eatda.infrastructure.persistance.entity.user.UserStatus
import io.eatda.infrastructure.persistance.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun registerUser(body: SignupUserRequest): UserIdResponse {
        val user = userRepository.save(body.toEntity())
        return UserIdResponse.form(user)
    }

    fun modifyUsername(body: UpdateUsernameRequest, user: User) {
        if(user.name == body.name) throw ApiException(UserErrorStatus.NAME_IS_SAME, body.name)
        if(userRepository.existsUserByName(body.name)) throw ApiException(UserErrorStatus.NAME_IS_ALREADY_USED, body.name)

        user.name = body.name
    }

    // soft delete
    fun withdrawUser(user: User) {
        if(user.activeStatus != UserStatus.ACTIVE) throw ApiException(UserErrorStatus.USER_IS_NOT_ACTIVE)

        user.activeStatus = UserStatus.DELETED
    }

    // Internal use only - not intended for facade layer
    // Use this method for internal service logic and domain operations
    fun getUser(identifier: UserIdentifier): User =
        when (identifier) {
            is UserIdentifier.ById -> userRepository.findByIdOrNull(identifier.id)
            is UserIdentifier.ByProvider -> userRepository.findUserByProviderId(identifier.providerId)
        } ?: throw ApiException(UserErrorStatus.USER_IS_NOT_FOUND)
}

sealed interface UserIdentifier {
    data class ById(
        val id: UUID,
    ) : UserIdentifier

    data class ByProvider(
        val providerId: String,
    ) : UserIdentifier
}