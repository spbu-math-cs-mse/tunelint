package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.model.core.Melody

class SetClefRequest(
    private val clef: Clef,
) : ChangeRequest {
    private var _removed: Clef? = null
    val removed get() = _removed

    override fun toString(): String = "Clef set to $clef"

    override fun execute(melody: Melody): Boolean {
        _removed = melody.clef
        melody.setClef(clef)
        return true
    }
}
