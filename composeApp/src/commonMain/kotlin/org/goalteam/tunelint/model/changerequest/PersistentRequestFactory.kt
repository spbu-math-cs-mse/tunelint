package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.changerequest.impl.AddSymbolPersistentRequest
import org.goalteam.tunelint.model.changerequest.impl.RemoveSymbolPersistentRequest
import org.goalteam.tunelint.model.core.Symbol

class PersistentRequestFactory {
    fun addSymbol(
        measure: Int,
        position: Int,
        symbol: Symbol,
    ): PersistentRequest = AddSymbolPersistentRequest(measure, position, symbol)

    fun removeSymbol(
        measure: Int,
        position: Int,
    ): PersistentRequest = RemoveSymbolPersistentRequest(measure, position)
}
