package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.Symbol

class RemoveSymbolRequest(
    private val notePointer: NotePointer,
) : ChangeRequest {
    private var _removed: Symbol? = null
    val removed get() = _removed

    override fun toString(): String = "remove symbol in position $notePointer"

    override fun execute(melody: Melody): Boolean {
        melody.mutateMeasures { mutableMeasures ->
            _removed =
                mutableMeasures[notePointer.measure(melody)]
                    .symbols
                    .getOrNull(notePointer.position(melody))
                    ?.clone()
        }

        var success = false
        melody.mutateMeasures { mutableMeasures ->
            success =
                mutableMeasures[notePointer.measure(melody)]
                    .removeSymbol(notePointer.position(melody))
        }
        return success
    }
}
