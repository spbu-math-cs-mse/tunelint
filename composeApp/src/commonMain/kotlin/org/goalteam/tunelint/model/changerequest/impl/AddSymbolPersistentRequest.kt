package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolPersistentRequest(
    measure: Int,
    position: Int,
    symbol: Symbol,
) : PersistentRequest {
    override val directRequest = AddSymbolRequest(measure, position, symbol)

    override val reverseRequest = RemoveSymbolRequest(measure, position)
}
