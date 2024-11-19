package org.goalteam.tunelint.model.core

interface ImmutableMelody : Cloneable {
    val name: String

    val timeSignature: TimeSignature

    val measures: List<ImmutableMeasure>
}
