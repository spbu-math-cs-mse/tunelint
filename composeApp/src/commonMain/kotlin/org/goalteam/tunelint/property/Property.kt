package org.goalteam.tunelint.property

interface Property<T> {
    fun identifier(): String

    fun value(): T
}
