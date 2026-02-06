package io.promoflow.api.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/users")
class UserController(

) {
    /*@PostMapping("/signup")
    fun signUpApi(
        @Valid @RequestBody body: SignupUserRequest,
    ): ResponseEntity<UserIdResponse> =
        handleApi(
            status = HttpStatus.CREATED,
            validator = { userValidator.validateSignUp(body) },
            supplier = { userService.signup(body) },
        )*/
}