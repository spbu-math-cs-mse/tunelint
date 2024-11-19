package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.changerequest.impl.AddSymbolToMelodyRequest
import org.goalteam.tunelint.model.changerequest.impl.RemoveSymbolFromMelodyRequest
import org.goalteam.tunelint.model.core.MutableMelody
import org.goalteam.tunelint.model.core.Symbol

class MelodyChangeRequestFactory {
    fun addSymbol(
        measure: Int,
        note: Int,
        symbol: Symbol,
        melody: MutableMelody,
    ): ChangeRequest<MutableMelody> = AddSymbolToMelodyRequest(measure, note, symbol, melody)

    fun removeSymbol(
        measure: Int,
        note: Int,
        melody: MutableMelody,
    ): ChangeRequest<MutableMelody> = RemoveSymbolFromMelodyRequest(measure, note, melody)
}
