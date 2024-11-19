package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.changerequest.impl.UpdateMelodyRequest
import org.goalteam.tunelint.model.core.Melody

class ChangeRequestFactory {
    fun synchronizationRequest(melody: Melody): ChangeRequest = UpdateMelodyRequest(melody)
}
