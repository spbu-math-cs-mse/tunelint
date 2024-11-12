package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.MutableMeasure
import org.goalteam.tunelint.model.core.Symbol

class MeasureImpl(
    private val symbols: MutableList<Symbol>,
) : MutableMeasure {
    override fun symbols() = symbols

    override fun symbolsMut(): MutableList<Symbol> = symbols

    override fun notify(notification: ChangeRequest<MutableMeasure>) {
        TODO("Not yet implemented")
    }
}
