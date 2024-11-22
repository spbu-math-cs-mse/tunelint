package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.core.NotePointer

class RemoveSymbolPersistentRequest(
    notePointer: NotePointer,
) : PersistentRequest {
    override val directRequest = RemoveSymbolRequest(notePointer)

    override val reverseRequest =
        AddSymbolRequest(
            notePointer,
            directRequest.removed
                ?: throw Exception("persistent request reverted before executing"),
        )
}
