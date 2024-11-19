package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolRequest(
    private val measure: Int,
    private val note: Int,
    private val symbol: Symbol,
) : ChangeRequest {
    override fun toString(): String = "add symbol $symbol on position $note in measure $measure"

    override fun execute(melody: Melody) =
        melody
            .mutableMeasures()[measure]
            .addSymbol(note, symbol)
}
