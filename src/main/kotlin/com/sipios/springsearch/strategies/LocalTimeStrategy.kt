package com.sipios.springsearch.strategies

import com.sipios.springsearch.SearchOperation
import java.time.LocalTime
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Path
import javax.persistence.criteria.Predicate
import kotlin.reflect.KClass

class LocalTimeStrategy : ParsingStrategy {
    override fun buildPredicate(
        builder: CriteriaBuilder,
        path: Path<*>,
        fieldName: String,
        ops: SearchOperation?,
        value: Any?
    ): Predicate? {
        return when (ops) {
            SearchOperation.GREATER_THAN -> builder.greaterThan(path.get(fieldName), value as LocalTime)
            SearchOperation.LESS_THAN -> builder.lessThan(path.get(fieldName), value as LocalTime)
            else -> super.buildPredicate(builder, path, fieldName, ops, value)
        }
    }

    override fun parse(value: String?, fieldClass: KClass<out Any>): Any? {
        return LocalTime.parse(value)
    }
}
