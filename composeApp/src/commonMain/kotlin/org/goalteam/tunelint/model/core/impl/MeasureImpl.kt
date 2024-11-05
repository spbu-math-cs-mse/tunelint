package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Symbol

class MeasureImpl(
    private val symbols: MutableList<Symbol>,
) : Measure {
    override fun symbols() = symbols
}
