package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Melody

class RemoveMeasureRequest(
    private val position: Int,
) : ChangeRequest {
    override fun toString() = "remove measure from position $position"

    override fun execute(melody: Melody) = melody.removeMeasure(position)
}
