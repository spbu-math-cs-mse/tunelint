package org.goalteam.tunelint.model.changerequest

import org.goalteam.tunelint.model.changerequest.impl.AddSymbolPersistentRequest
import org.goalteam.tunelint.model.changerequest.impl.RemoveSymbolPersistentRequest
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.Symbol

class PersistentRequestFactory {
    fun addSymbol(
        notePointer: NotePointer,
        symbol: Symbol,
    ): PersistentRequest = AddSymbolPersistentRequest(notePointer, symbol)

    fun removeSymbol(notePointer: NotePointer): PersistentRequest = RemoveSymbolPersistentRequest(notePointer)
}
