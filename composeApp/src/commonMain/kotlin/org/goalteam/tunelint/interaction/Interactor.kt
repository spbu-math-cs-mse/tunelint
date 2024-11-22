package org.goalteam.tunelint.interaction

import org.goalteam.tunelint.interaction.events.*
import org.goalteam.tunelint.interaction.handlers.InteractionHandlerFactory
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.core.PrimaryNoteValue

/**
 * Ready to use tool for frontend to communicate with model
 */
class Interactor(
    private val manager: PersistenceManager,
) {
    private val configuration = InteractionHandlerFactory().createConfiguration()
    private val receiver = InteractionHandlerFactory().createReceiver(configuration, manager)

    fun setValue(value: PrimaryNoteValue) {
        configuration.setValue(value)
    }

    fun setMode(mode: Mode) {
        configuration.setMode(mode)
    }

    fun handleButton(command: CommandType) {
        val event = EventFactory().createCommandButtonInteractionData(command)
        receiver.handleButton(event)
    }

    fun handleAction(
        stage: Int,
        position: Int,
        measure: Int,
        side: Side,
        action: Action,
    ) {
        val event = EventFactory().createStaffInteractionData(stage, position, measure, side, action)
        receiver.handleAction(event)
    }
}
