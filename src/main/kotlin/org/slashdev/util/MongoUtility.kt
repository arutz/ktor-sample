package org.slashdev.util

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import kotlin.reflect.KProperty
import kotlin.reflect.full.hasAnnotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
annotation class DocumentId

infix fun <
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    @kotlin.internal.OnlyInputTypes
    V> KProperty<V>.eq(value: V) = run {
  if (this.hasAnnotation<DocumentId>()) Filters.eq("_id", value) else Filters.eq(this.name, value)
}

infix fun <
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    @kotlin.internal.OnlyInputTypes
    V : Any> KProperty<V>.gt(value: V) = Filters.gt(this.name, value)

infix fun <
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    @kotlin.internal.OnlyInputTypes
    V : Any> KProperty<V>.lt(value: V) = Filters.lt(this.name, value)

infix fun <
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    @kotlin.internal.OnlyInputTypes
    V : Any> KProperty<V>.gte(value: V) = Filters.gte(this.name, value)

infix fun <
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    @kotlin.internal.OnlyInputTypes
    V : Any> KProperty<V>.lte(value: V) = Filters.lte(this.name, value)

infix fun <
    @Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")
    @kotlin.internal.OnlyInputTypes
    V> KProperty<V>.setTo(value: V) = Updates.set(this.name, value)
