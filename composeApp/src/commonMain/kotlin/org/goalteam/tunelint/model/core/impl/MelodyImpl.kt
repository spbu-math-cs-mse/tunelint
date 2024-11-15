package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.MutableMeasure
import org.goalteam.tunelint.model.core.MutableMelody

internal class MelodyImpl(
    private val name: String,
    measures: List<MutableMeasure>,
) : MutableMelody {
    private val measures: MutableList<MutableMeasure> = measures.toMutableList()

    override fun name(): String = this.name

    override fun measures(): List<Measure> = measures

    override fun measuresMut(): MutableList<MutableMeasure> = measures

    override fun notify(notification: ChangeRequest<MutableMelody>) {
        if (notification.isExecutable()) {
            notification.execute()
        } else {
            println("bad request")
            return
        }
    }
}
