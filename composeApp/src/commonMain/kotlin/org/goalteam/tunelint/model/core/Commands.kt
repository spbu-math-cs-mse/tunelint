package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.musicsheetchangerequest.AddSymbolRequest
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.musicsheetchangerequest.RemoveSymbolRequest

class Commands(
    private val sheet: Melody,
) {
    fun addSymbol(
        measure: Int,
        note: Int,
        symbol: Symbol,
    ): MusicSheetChangeRequest = AddSymbolRequest(measure, note, symbol, sheet)

    fun removeSymbol(
        measure: Int,
        note: Int,
    ): MusicSheetChangeRequest = RemoveSymbolRequest(measure, note, sheet)
}
