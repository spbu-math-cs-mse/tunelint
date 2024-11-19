package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.syncWith

class UpdateMelodyRequest(
    private val melody: Melody,
) : ChangeRequest {
    override fun toString() = "Update melody to $melody"

    override fun execute(melody: Melody) {
        melody.syncWith(this.melody)
    }
}
