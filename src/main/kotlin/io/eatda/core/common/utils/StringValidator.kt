package io.eatda.core.common.utils

object StringValidator {
    private val SPECIAL_CHAR_REGEX = Regex("[^A-Za-z0-9가-힣\\s]")
    fun String.containsSpecialCharacter(): Boolean = SPECIAL_CHAR_REGEX.containsMatchIn(this)
}
