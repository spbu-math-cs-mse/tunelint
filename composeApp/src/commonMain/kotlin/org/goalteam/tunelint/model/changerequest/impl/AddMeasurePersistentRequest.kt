package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.TimeSignature

class AddMeasurePersistentRequest(
    position: Int,
) : PersistentRequest {
    override val directRequest: ChangeRequest =
        AddMeasureRequest(position, MusicFactory().createMeasure(TimeSignature.standardTime))
    override val reverseRequest: ChangeRequest = RemoveMeasureRequest(position)
}
