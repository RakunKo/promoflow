package io.eatda

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class PlantoryApplication

fun main(args: Array<String>) {
    runApplication<PlantoryApplication>(*args)
}