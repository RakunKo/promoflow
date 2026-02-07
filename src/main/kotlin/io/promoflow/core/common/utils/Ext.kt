package io.promoflow.core.common.utils

fun String.formatErrorMessage(vararg args: Any): String =
    if (args.isEmpty()) this
    else this.format(*args)

fun <T, E> T.validateStatus(
    allowStatus: List<E>,
    statusSelector: (T) -> E,
    exception: () -> Throwable
): T {
    if(statusSelector(this) !in allowStatus) throw exception()
    return this
}

fun <T, E> T.validateStatusNot(
    notAllowStatus: List<E>,
    statusSelector: (T) -> E,
    exception: () -> Throwable
): T {
    if(statusSelector(this) in notAllowStatus) throw exception()
    return this
}

