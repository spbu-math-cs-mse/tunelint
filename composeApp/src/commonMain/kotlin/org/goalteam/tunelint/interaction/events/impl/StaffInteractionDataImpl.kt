package org.goalteam.tunelint.interaction.events.impl

import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.StaffInteractionData

class StaffInteractionDataImpl(
    private val stage: Int,
    private val position: Int,
    private val measure: Int,
    private val action: Action,
) : StaffInteractionData {
    override fun stage(): Int = stage

    override fun position(): Int = position

    override fun measure(): Int = measure

    override fun action(): Action = action
}
