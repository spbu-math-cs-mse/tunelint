package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.MutableMeasure
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolToMeasureRequest(
    private val note: Int,
    private val symbol: Symbol,
    private val subject: MutableMeasure,
) : ChangeRequest<MutableMeasure> {
    override fun toString(): String = "add symbol $symbol at $note to $subject"

    override fun execute() {
        subject.symbolsMut().add(note, symbol)
    }

    override fun isExecutable(): Boolean = enoughNotes(note - 1)

    override fun revert() {
        subject.symbolsMut().removeAt(note)
    }

    override fun isRevertible(): Boolean = enoughNotes(note)

    private fun enoughNotes(size: Int): Boolean = subject.symbols().size > size
}
