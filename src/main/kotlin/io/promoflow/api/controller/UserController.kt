package io.promoflow.api.controller

import io.promoflow.api.dto.user.SignupUserRequest
import io.promoflow.api.dto.user.UserIdResponse
import io.promoflow.core.handler.handleApi
import io.promoflow.core.service.UserService
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