package org.goalteam.tunelint.model.core

interface Melody :
    ImmutableMelody,
    MutableMelody,
    Cloneable {
    public override fun clone(): Melody
}

fun Melody.syncWith(other: Melody) {
    setName(other.name)
    setTimeSignature(other.timeSignature)
    setMeasures(other.mutableMeasures())
    setClef(other.clef)
}
