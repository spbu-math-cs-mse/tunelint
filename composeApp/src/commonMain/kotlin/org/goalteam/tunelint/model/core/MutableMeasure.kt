package org.goalteam.tunelint.model.core

interface MutableMeasure : Measure {
    fun symbolsMut(): MutableList<Symbol>
}
