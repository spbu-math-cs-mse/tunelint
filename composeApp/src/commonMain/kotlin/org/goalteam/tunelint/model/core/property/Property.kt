package org.goalteam.tunelint.model.core.property

interface Property<T> {
    fun identifier(): String

    fun value(): T
}
