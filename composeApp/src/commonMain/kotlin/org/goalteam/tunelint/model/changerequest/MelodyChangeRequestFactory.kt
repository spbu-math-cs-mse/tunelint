package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.changerequest.impl.AddSymbolToMelodyRequest
import org.goalteam.tunelint.model.core.MutableMelody
import org.goalteam.tunelint.model.core.Symbol

class MelodyChangeRequestFactory {
    fun addSymbol(
        measure: Int,
        note: Int,
        symbol: Symbol,
    ): ChangeRequest<MutableMelody> = AddSymbolToMelodyRequest(measure, note, symbol)

    fun removeSymbol(
        measure: Int,
        note: Int,
    ): ChangeRequest<MutableMelody> = TODO("not implemented yet")
}
