package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolRequest(
    private val notePointer: NotePointer,
    private val symbol: Symbol,
) : ChangeRequest {
    override fun toString(): String = "add symbol $symbol on position $notePointer}"

    override fun execute(melody: Melody): Boolean {
        var success = false
        melody.mutateMeasures { mutableMeasures: List<Measure> ->
            success =
                mutableMeasures[notePointer.measure(melody)]
                    .addSymbol(notePointer.position(melody), symbol)
        }
        return success
    }
}
