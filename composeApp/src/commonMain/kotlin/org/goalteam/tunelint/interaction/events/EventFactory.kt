package org.goalteam.tunelint.interaction.events

import org.goalteam.tunelint.interaction.events.impl.CommandButtonInteractionDataImpl
import org.goalteam.tunelint.interaction.events.impl.StaffInteractionDataImpl

class EventFactory {
    fun createStaffInteractionData(
        stage: Int,
        position: Int,
        measure: Int,
        action: Action,
    ): StaffInteractionData = StaffInteractionDataImpl(stage, position, measure, action)

    fun createCommandButtonInteractionData(command: CommandType): CommandButtonInteractionData = CommandButtonInteractionDataImpl(command)
}
