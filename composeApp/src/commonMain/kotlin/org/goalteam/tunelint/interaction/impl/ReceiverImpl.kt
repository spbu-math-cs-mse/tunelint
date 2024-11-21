package org.goalteam.tunelint.interaction.impl

import org.goalteam.tunelint.interaction.*
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.changerequest.PersistentRequestFactory
import org.goalteam.tunelint.model.core.MusicFactory

class ReceiverImpl(
    private val configuration: RedactorConfiguration,
    private val persistenceManager: PersistenceManager,
) : Receiver {
    private val requestFactory = PersistentRequestFactory()
    private val musicFactory = MusicFactory()

    override fun handleButton(button: CommandButtonInteractionData) {
        if (button.command() == CommandType.Undo) {
            persistenceManager.undo()
        }
        if (button.command() == CommandType.Redo) {
            persistenceManager.redo()
        }
    }

    override fun handleAction(action: StaffInteractionData) {
        val note = musicFactory.createNote(action.stage(), configuration.getValue())
        val request = requestFactory.addSymbol(action.measure(), action.position(), note)
        persistenceManager.notify(request)
    }
}
