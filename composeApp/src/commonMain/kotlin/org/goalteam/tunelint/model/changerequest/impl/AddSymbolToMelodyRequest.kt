package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.MutableMelody
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolToMelodyRequest(
    private val measure: Int,
    private val note: Int,
    private val symbol: Symbol,
) : ChangeRequest<MutableMelody> {
    private val measureRequest = AddSymbolToMeasureRequest(note, symbol)

    override fun toString(): String = "add symbol $symbol at $note in $measure measure"

    override fun execute(subject: MutableMelody) {
        measureRequest.execute(subject.measuresMut()[measure])
    }

    override fun isExecutable(): Boolean = true

    override fun revert(subject: MutableMelody) {
        measureRequest.revert(subject.measuresMut()[measure])
    }

    override fun isRevertible(): Boolean = true
}
