package org.goalteam.tunelint.interaction.handlers.impl

import org.goalteam.tunelint.interaction.events.*
import org.goalteam.tunelint.interaction.handlers.Receiver
import org.goalteam.tunelint.interaction.handlers.RedactorConfiguration
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.changerequest.PersistentRequestFactory
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.PointerFactory

class ReceiverImpl(
    private val configuration: RedactorConfiguration,
    private val persistenceManager: PersistenceManager,
) : Receiver {
    private val requestFactory = PersistentRequestFactory()
    private val musicFactory = MusicFactory()
    private val pointerFactory = PointerFactory()

    override fun handleButton(button: CommandButtonInteractionData) {
        if (button.command() == CommandType.Undo) {
            persistenceManager.undo()
        }
        if (button.command() == CommandType.Redo) {
            persistenceManager.redo()
        }
    }

    override fun handleAction(action: StaffInteractionData) {
        val request = createRequest(action)
        persistenceManager.notify(request)
    }

    private fun createRequest(action: StaffInteractionData): PersistentRequest {
        val note = musicFactory.createNote(action.stage(), configuration.getValue())
        val pointer = pointerFactory.createNotePointerSimple(action.measure(), action.position())
        if (action.action() == Action.Move) {
            TODO("Preview is not supported yet")
        }
        when (configuration.getMode()) {
            Mode.AddNote -> {
                if (action.side() == Side.Right) {
                    return requestFactory.addSymbol(
                        pointer.next(),
                        note,
                    )
                }

                return requestFactory.addSymbol(pointer, note)
            }
            Mode.InsertNote -> TODO("Insert is not supported yet")
            Mode.DeleteNote -> return requestFactory.removeSymbol(pointer)
            Mode.AddMeasure -> return requestFactory.addMeasure(action.measure())
            Mode.DeleteMeasure -> return requestFactory.removeMeasure(action.measure())
        }
    }
}
