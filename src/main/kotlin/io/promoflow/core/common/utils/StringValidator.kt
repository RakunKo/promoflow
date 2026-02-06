package nexio.apiserver.application.common.utils

object StringValidator {
    private val SPECIAL_CHAR_REGEX = Regex("[^A-Za-z0-9가-힣]")

    fun String.containsSpecialCharacter(): Boolean = SPECIAL_CHAR_REGEX.containsMatchIn(this)
}
