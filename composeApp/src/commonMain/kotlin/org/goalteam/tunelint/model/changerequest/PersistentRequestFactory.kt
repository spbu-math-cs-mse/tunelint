package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.changerequest.impl.AddMeasurePersistentRequest
import org.goalteam.tunelint.model.changerequest.impl.AddSymbolPersistentRequest
import org.goalteam.tunelint.model.changerequest.impl.ChangeAccidentalPersistentRequest
import org.goalteam.tunelint.model.changerequest.impl.RemoveMeasurePersistentRequest
import org.goalteam.tunelint.model.changerequest.impl.RemoveSymbolPersistentRequest
import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.Symbol

class PersistentRequestFactory {
    fun addSymbol(
        notePointer: NotePointer,
        symbol: Symbol,
    ): PersistentRequest = AddSymbolPersistentRequest(notePointer, symbol)

    fun removeSymbol(notePointer: NotePointer): PersistentRequest =
        RemoveSymbolPersistentRequest(notePointer)

    fun addMeasure(position: Int): PersistentRequest = AddMeasurePersistentRequest(position)

    fun removeMeasure(position: Int): PersistentRequest = RemoveMeasurePersistentRequest(position)

    fun changeAccidental(
        notePointer: NotePointer,
        accidental: Accidental?
    ): PersistentRequest = ChangeAccidentalPersistentRequest(notePointer, accidental)
}
