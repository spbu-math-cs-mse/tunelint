package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.*

class RemoveSymbolFromMelodyRequest(
    private val measure: Int,
    private val note: Int,
    private val sheet: MutableMelody,
) : ChangeRequest<MutableMelody> {
    override fun toString(): String = "remove note at $note in $measure measure"

    override fun execute() {
        measureRequest().execute()
    }

    override fun isExecutable(): Boolean = enoughMeasures() && measureRequest().isExecutable()

    override fun revert() {
        measureRequest().revert()
    }

    override fun isRevertible(): Boolean = enoughMeasures() && measureRequest().isRevertible()

    private fun measureRequest(): ChangeRequest<MutableMeasure> = RemoveSymbolFromMeasureRequest(note, sheet.measuresMut()[measure])

    private fun enoughMeasures(): Boolean = sheet.measures().size > measure
}
