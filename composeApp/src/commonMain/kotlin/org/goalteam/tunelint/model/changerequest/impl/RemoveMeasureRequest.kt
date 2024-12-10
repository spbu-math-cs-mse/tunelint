package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody

class RemoveMeasureRequest(
    private val position: Int,
) : ChangeRequest {
    private var _removed: Measure? = null
    val removed get() = _removed

    override fun toString() = "remove measure from position $position"

    override fun execute(melody: Melody): Boolean {
        _removed = melody.mutableMeasures().getOrNull(position)?.clone()
        return melody.removeMeasure(position)
    }
}
