package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.MutableMeasure
import org.goalteam.tunelint.model.core.Symbol

class RemoveSymbolFromMeasureRequest(
    private val note: Int,
    private val measure: MutableMeasure,
) : ChangeRequest<MutableMeasure> {
    private var removed: Symbol? = null

    override fun toString(): String = "remove $note symbol from $measure"

    override fun execute() {
        measure.symbolsMut().add(note, removed!!)
    }

    override fun isExecutable(): Boolean = enoughNotes(note)

    override fun revert() {
        removed = measure.symbolsMut().removeAt(note)
    }

    override fun isRevertible(): Boolean = enoughNotes(note - 1) && removed != null

    private fun enoughNotes(size: Int): Boolean = measure.symbols().size > size
}
