package org.goalteam.tunelint.model.musicsheetcontainer

import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.musicsheetchangerequest.AddSymbolRequest
import org.goalteam.tunelint.model.musicsheetchangerequest.MusicSheetChangeRequest
import org.goalteam.tunelint.model.musicsheetchangerequest.RemoveSymbolRequest

class Commands(
    private val sheet: MusicSheet,
) {
    fun addNote(
        measure: Int,
        note: Int,
        value: Int,
        length: Float,
    ): MusicSheetChangeRequest = AddSymbolRequest(measure, note, MusicFactory().createNote(value, length), sheet)

    fun addEmpty(
        measure: Int,
        note: Int,
        length: Float,
    ): MusicSheetChangeRequest = AddSymbolRequest(measure, note, MusicFactory().createEmpty(length), sheet)

    fun removeSymbol(
        measure: Int,
        note: Int,
    ): MusicSheetChangeRequest = RemoveSymbolRequest(measure, note, sheet)
}
