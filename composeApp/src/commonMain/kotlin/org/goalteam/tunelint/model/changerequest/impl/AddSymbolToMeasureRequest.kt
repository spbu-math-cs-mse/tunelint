package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.MutableMeasure
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolToMeasureRequest(
    private val note: Int,
    private val symbol: Symbol,
) : ChangeRequest<MutableMeasure> {
    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override fun execute(subject: MutableMeasure) {
        subject.symbolsMut().add(note, symbol)
    }

    override fun isExecutable(): Boolean {
        TODO("Not yet implemented")
    }

    override fun revert(subject: MutableMeasure) {
        subject.symbolsMut().removeAt(note)
    }

    override fun isRevertible(): Boolean {
        TODO("Not yet implemented")
    }
}
