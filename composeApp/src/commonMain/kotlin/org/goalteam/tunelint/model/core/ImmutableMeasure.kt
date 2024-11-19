package org.goalteam.tunelint.model.core

interface ImmutableMeasure : Cloneable {
    val timeSignature: TimeSignature

    val remainingValue: NoteValue

    val symbols: List<Symbol>
}

val ImmutableMeasure.totalValue
    get() = timeSignature.value()

val ImmutableMeasure.occupiedValue
    get() = totalValue - remainingValue

fun ImmutableMeasure.canAdd(symbol: Symbol) = remainingValue >= symbol.value()

fun ImmutableMeasure.isFull() = remainingValue == NoteValue.Nil
