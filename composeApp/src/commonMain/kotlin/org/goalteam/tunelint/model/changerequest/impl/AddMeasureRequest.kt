package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody

class AddMeasureRequest(
    private val position: Int,
    private val measure: Measure,
) : ChangeRequest {
    override fun toString() = "add measure $measure on position $position"

    override fun execute(melody: Melody) = melody.addMeasure(position, measure)
}
