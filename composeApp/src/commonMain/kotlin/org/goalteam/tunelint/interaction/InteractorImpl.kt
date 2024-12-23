package org.goalteam.tunelint.interaction

import org.goalteam.tunelint.interaction.events.*
import org.goalteam.tunelint.interaction.handlers.InteractionHandlerFactory
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.PrimaryNoteValue

class InteractorImpl(
    private val manager: PersistenceManager,
) : Interactor {
    private val configuration = InteractionHandlerFactory().createConfiguration()
    private val receiver = InteractionHandlerFactory().createReceiver(configuration, manager)

    private val modeSubscribers = mutableListOf<Notifiable<Boolean>>()

    override fun setValue(value: PrimaryNoteValue) {
        configuration.setValue(value)
        for (subscriber in modeSubscribers) {
            subscriber.notify(true)
        }
    }

    override fun getValue(): PrimaryNoteValue = configuration.getValue()

    override fun setMode(mode: Mode) {
        configuration.setMode(mode)
        for (subscriber in modeSubscribers) {
            subscriber.notify(true)
        }
    }

    override fun getMode(): Mode = configuration.getMode()

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
        val event =
            EventFactory().createStaffInteractionData(stage, position, measure, side, action)
        receiver.handleAction(event)
    }

    override fun changeClef(oldClef: Clef) {
        receiver.changeClef(oldClef)
    }

    override fun subscribe(subscriber: Notifiable<Boolean>) {
        modeSubscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<Boolean>) {
        modeSubscribers.remove(subscriber)
    }

    override fun synchronize(caller: Notifiable<Boolean>) {
        caller.notify(true)
    }
}
