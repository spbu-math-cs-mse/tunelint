package org.goalteam.tunelint.interaction

import org.goalteam.tunelint.interaction.events.*
import org.goalteam.tunelint.interaction.handlers.InteractionHandlerFactory
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.PrimaryNoteValue




class InteractorImpl(
    private val manager: PersistenceManager,
) : Interactor {
    private val configuration = InteractionHandlerFactory().createConfiguration()
    private val receiver = InteractionHandlerFactory().createReceiver(configuration, manager)

    private val modeSubscribers = mutableListOf<Notifiable<CurrentMode>>()
    override fun setValue(value: PrimaryNoteValue) {
        configuration.setValue(value)
    }

    override fun setMode(mode: Mode) {
        configuration.setMode(mode)
        for(subscriber in modeSubscribers){
            subscriber.notify(CurrentMode(configuration.getMode()))
        }
    }

    override fun handleButton(command: CommandType) {
        val event = EventFactory().createCommandButtonInteractionData(command)
        receiver.handleButton(event)
    }

    override fun handleAction(
        stage: NoteOffset,
        position: Int,
        measure: Int,
        side: Side,
        action: Action,
    ) {
        val event = EventFactory().createStaffInteractionData(stage, position, measure, side, action)
        receiver.handleAction(event)
    }

    override fun subscribe(subscriber: Notifiable<CurrentMode>) {
        modeSubscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<CurrentMode>) {
        modeSubscribers.remove(subscriber)
    }

    override fun synchronize(caller: Notifiable<CurrentMode>) {
        caller.notify(CurrentMode(configuration.getMode()))
    }
}
