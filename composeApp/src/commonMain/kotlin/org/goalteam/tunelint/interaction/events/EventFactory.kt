package org.goalteam.tunelint.interaction.events

import org.goalteam.tunelint.interaction.events.impl.CommandButtonInteractionDataImpl
import org.goalteam.tunelint.interaction.events.impl.StaffInteractionDataImpl
import org.goalteam.tunelint.model.core.NoteOffset

class EventFactory {
    fun createStaffInteractionData(
        stage: NoteOffset,
        position: Int,
        measure: Int,
        side: Side,
        action: Action,
    ): StaffInteractionData = StaffInteractionDataImpl(stage, position, measure, side, action)

    fun createCommandButtonInteractionData(command: CommandType): CommandButtonInteractionData = CommandButtonInteractionDataImpl(command)
}
