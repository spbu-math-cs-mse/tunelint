package org.goalteam.tunelint.model.core

interface MutableMelody : Melody {
    fun measuresMut(): MutableList<MutableMeasure>
}
