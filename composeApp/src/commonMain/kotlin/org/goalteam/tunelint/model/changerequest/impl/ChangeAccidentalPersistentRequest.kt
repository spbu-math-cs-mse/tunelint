package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.model.core.NotePointer

class ChangeAccidentalPersistentRequest (
    private val notePointer: NotePointer,
    private val accidental: Accidental?,
    ) : PersistentRequest {
    override val directRequest = ChangeAccidentalRequest(notePointer, accidental)
    override val reverseRequest get() =
        ChangeAccidentalRequest(notePointer, directRequest.oldAccidental)

}