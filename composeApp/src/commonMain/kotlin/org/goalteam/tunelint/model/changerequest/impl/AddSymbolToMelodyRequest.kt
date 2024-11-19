package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.MutableMelody
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolToMelodyRequest(
    private val measure: Int,
    private val note: Int,
    private val symbol: Symbol,
    private val subject: MutableMelody,
) : ChangeRequest<MutableMelody> {
    override fun toString(): String = "add symbol $symbol at $note in $measure measure"

    override fun execute() {
        measureRequest().execute()
    }

    override fun isExecutable(): Boolean = enoughMeasures() && measureRequest().isExecutable()

    override fun revert() {
        measureRequest().revert()
    }

    override fun isRevertible(): Boolean = enoughMeasures() && measureRequest().isRevertible()

    private fun enoughMeasures(): Boolean = subject.measuresMut().size > measure

    private fun measureRequest(): AddSymbolToMeasureRequest = AddSymbolToMeasureRequest(note, symbol, subject.measuresMut()[measure])
}
