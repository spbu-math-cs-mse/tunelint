package org.goalteam.tunelint.interaction.handlers.impl

import org.goalteam.tunelint.interaction.events.*
import org.goalteam.tunelint.interaction.handlers.Receiver
import org.goalteam.tunelint.interaction.handlers.RedactorConfiguration
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.changerequest.PersistentRequest
import org.goalteam.tunelint.model.changerequest.PersistentRequestFactory
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.NotePointer
import org.goalteam.tunelint.model.core.PointerFactory
import org.goalteam.tunelint.model.core.Symbol

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
        val pointer = pointerFactory.createNotePointerSimple(action.measure(), action.position())
        if (action.action() == Action.Move) {
            TODO("Preview is not supported yet")
        }
        return when (configuration.getMode()) {
            Mode.AddNote -> addSymbol(action, pointer, newNote(action))
            Mode.DeleteNote -> requestFactory.removeSymbol(pointer)
            Mode.AddMeasure -> requestFactory.addMeasure(action.measure() + 1)
            Mode.DeleteMeasure -> requestFactory.removeMeasure(action.measure())
            Mode.AddRest -> addSymbol(action, pointer, newRest())
        }
    }

    private fun newNote(action: StaffInteractionData) = musicFactory.createNote(action.stage(), configuration.getValue())

    private fun newRest() = musicFactory.createRest(configuration.getValue())

    private fun addSymbol(
        action: StaffInteractionData,
        pointer: NotePointer,
        symbol: Symbol,
    ): PersistentRequest =
        requestFactory.addSymbol(
            if (action.side() == Side.Right) {
                pointer.next()
            } else {
                pointer
            },
            symbol,
        )
}
