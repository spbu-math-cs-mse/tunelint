package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.core.Clef

class SetClefPersistentRequest(
    private val clef: Clef,
) : PersistentRequest {
    override val directRequest = SetClefRequest(clef)

    override val reverseRequest
        get() =
            SetClefRequest(
                directRequest.removed
                    ?: throw Exception("persistent request reverted before executing"),
            )
}
