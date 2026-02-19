package io.eatda.infrastructure.security.resolver

import io.eatda.core.common.exception.ApiException
import io.eatda.core.common.status.CommonErrorStatus
import io.eatda.core.service.UserIdentifier
import io.eatda.core.service.UserService
import io.eatda.infrastructure.persistance.entity.user.User
import io.eatda.infrastructure.security.annotations.AuthUser
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.util.UUID

@Component
class AuthUserResolver(
    private val userService: UserService,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.hasParameterAnnotation(AuthUser::class.java) && parameter.parameterType == User::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val context = SecurityContextHolder.getContext()
        val jwt = context.authentication?.credentials as? Jwt
            ?: throw ApiException(CommonErrorStatus.UNAUTHORIZED)

        val userId = jwt.claims["id"]?.toString()?.let { UUID.fromString(it) }
            ?: throw ApiException(CommonErrorStatus.UNAUTHORIZED)

        return userService.getUser(UserIdentifier.ById(userId))
    }
}
