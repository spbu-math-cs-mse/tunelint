package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.Symbol

class RemoveSymbolRequest(
    private val notePointer: NotePointer,
) : ChangeRequest {
    private var _removed: Symbol? = null
    val removed = _removed

    override fun toString(): String = "remove symbol in position $notePointer"

    override fun execute(melody: Melody): Boolean {
        _removed =
            melody
                .mutableMeasures()[notePointer.measure(melody)]
                .symbols[notePointer.position(melody)]
                .clone()

        return melody
            .mutableMeasures()[notePointer.measure(melody)]
            .removeSymbol(notePointer.position(melody))
    }
}
