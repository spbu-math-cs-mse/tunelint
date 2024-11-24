package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolRequest(
    val notePointer: NotePointer,
    private val symbol: Symbol,
) : ChangeRequest {
    override fun toString(): String = "add symbol $symbol on position $notePointer}"

    override fun execute(melody: Melody) =
        melody
            .mutableMeasures()[notePointer.measure(melody)]
            .addSymbol(notePointer.position(melody), symbol)
}
