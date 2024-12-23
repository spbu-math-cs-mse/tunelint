package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.changerequest.impl.*
import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.Symbol

class PersistentRequestFactory {
    fun addSymbol(
        notePointer: NotePointer,
        symbol: Symbol,
    ): PersistentRequest = AddSymbolPersistentRequest(notePointer, symbol)

    fun removeSymbol(notePointer: NotePointer): PersistentRequest = RemoveSymbolPersistentRequest(notePointer)

    fun addMeasure(position: Int): PersistentRequest = AddMeasurePersistentRequest(position)

    fun removeMeasure(position: Int): PersistentRequest = RemoveMeasurePersistentRequest(position)

    fun setClef(clef: Clef): PersistentRequest = SetClefPersistentRequest(clef)
}
