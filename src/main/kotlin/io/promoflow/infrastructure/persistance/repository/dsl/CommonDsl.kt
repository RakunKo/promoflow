package io.promoflow.infrastructure.persistance.repository.dsl

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.ComparableExpression
import com.querydsl.core.types.dsl.SimpleExpression
import com.querydsl.jpa.impl.JPAQuery

abstract class CommonDsl {
    protected fun <T> JPAQuery<T>.pagination(page: Int, size: Int): JPAQuery<T> {
        return this.offset(page.toLong() * size)
            .limit(size.toLong())
    }

    protected fun <T> eq(path: SimpleExpression<T>, value: T?): BooleanExpression? {
        return value?.let { path.eq(it) }
    }

    protected fun <T: Comparable<*>> between(
        path: ComparableExpression<T>,
        from: T?,
        to: T?
    ): BooleanExpression? = when {
        from != null && to != null -> path.between(from, to)
        from != null -> path.goe(from)
        to != null -> path.loe(to)
        else -> null
    }
}