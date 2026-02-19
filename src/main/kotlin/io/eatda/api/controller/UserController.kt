package io.eatda.api.controller

import io.eatda.api.dto.user.SignupUserRequest
import io.eatda.api.dto.user.UserIdResponse
import io.eatda.core.handler.handleApi
import io.eatda.core.service.UserService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/signup")
    fun signUpApi(
        @Valid @RequestBody body: SignupUserRequest,
    ): ResponseEntity<UserIdResponse> =
        handleApi(
            status = HttpStatus.CREATED,
            supplier = { userService.registerUser(body) },
        )
}