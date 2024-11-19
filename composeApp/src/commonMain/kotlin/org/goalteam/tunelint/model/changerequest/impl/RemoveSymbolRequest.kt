package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.Symbol

class RemoveSymbolRequest(
    private val measure: Int,
    private val note: Int,
) : ChangeRequest {
    private var _removed: Symbol? = null
    val removed = _removed

    override fun toString(): String = "remove symbol $note from measure $measure"

    override fun execute(melody: Melody) {
        _removed =
            melody
                .mutableMeasures()[measure]
                .symbols[note]
                .clone()

        melody
            .mutableMeasures()[measure]
            .removeSymbol(note)
    }
}
