package org.goalteam.tunelint.interaction.events.impl

import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.Side
import org.goalteam.tunelint.interaction.events.StaffInteractionData

class StaffInteractionDataImpl(
    private val stage: Int,
    private val position: Int,
    private val measure: Int,
    private val side: Side,
    private val action: Action,
) : StaffInteractionData {
    override fun stage(): Int = stage

    override fun position(): Int = position

    override fun measure(): Int = measure

    override fun side(): Side = side

    override fun action(): Action = action
}
