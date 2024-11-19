package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.TimeSignature

class PushFrontEmptyMeasurePersistentRequest : PersistentRequest {
    override val directRequest =
        AddMeasureRequest(0, MusicFactory().createMeasure(TimeSignature.standardTime))

    override val reverseRequest =
        RemoveMeasureRequest(0)
}
