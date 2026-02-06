package nexio.apiserver.application.common.utils

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

object JsonUtils {
    val objectMapper: ObjectMapper =
        jacksonObjectMapper()
            .findAndRegisterModules()
            .registerKotlinModule()
}
