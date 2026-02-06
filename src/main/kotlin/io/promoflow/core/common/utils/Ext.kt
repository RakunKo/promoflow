package io.promoflow.core.common.utils

fun String.formatErrorMessage(vararg args: Any): String =
    if (args.isEmpty()) this
    else this.format(*args)

