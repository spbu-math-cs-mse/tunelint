package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistentRequest

class RemoveSymbolPersistentRequest(
    measure: Int,
    position: Int,
) : PersistentRequest {
    override val directRequest = RemoveSymbolRequest(measure, position)

    override val reverseRequest =
        AddSymbolRequest(
            measure,
            position,
            directRequest.removed
                ?: throw Exception("persistent request reverted before executing"),
        )
}
