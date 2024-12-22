package org.goalteam.tunelint.model.core

interface ImmutableMelody : Cloneable {
    val name: String

    val timeSignature: TimeSignature

    val clef: Clef

    val measures: List<ImmutableMeasure>
}

fun ImmutableMelody.symbols() = measures.flatMap { it.symbols }
