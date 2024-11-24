package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolPersistentRequest(
    notePointer: NotePointer,
    symbol: Symbol,
) : PersistentRequest {
    override val directRequest = AddSymbolRequest(notePointer, symbol)

    override val reverseRequest = RemoveSymbolRequest(notePointer)
}
