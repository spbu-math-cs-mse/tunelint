package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistentRequest

class RemoveMeasurePersistentRequest(
    private val position: Int,
) : PersistentRequest {
    override val directRequest = RemoveMeasureRequest(position)
    override val reverseRequest get() =
        AddMeasureRequest(
            position,
            directRequest.removed ?: throw Exception("persistent request reverted before executing((("),
        )
}
