package org.goalteam.tunelint.interaction

import org.goalteam.tunelint.interaction.events.*
import org.goalteam.tunelint.interaction.handlers.InteractionHandlerFactory
import org.goalteam.tunelint.model.changerequest.Notifiable
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.PrimaryNoteValue

class InteractorImpl(
    private val manager: PersistenceManager,
) : Interactor {
    private val configuration = InteractionHandlerFactory().createConfiguration()
    private val receiver = InteractionHandlerFactory().createReceiver(configuration, manager)

    private val modeSubscribers = mutableListOf<Notifiable<Unit>>()

    override fun setValue(value: PrimaryNoteValue) {
        configuration.setValue(value)
        for (subscriber in modeSubscribers) {
            subscriber.notify(Unit)
        }
    }

    override fun getValue(): PrimaryNoteValue = configuration.getValue()

    override fun setMode(mode: Mode) {
        configuration.setMode(mode)
        for (subscriber in modeSubscribers) {
            subscriber.notify(Unit)
        }
    }

    override fun getMode(): Mode = configuration.getMode()
    override fun setAccidental(accidental: Accidental?) {
        configuration.setAccidental(accidental)
        for (subscriber in modeSubscribers) {
            subscriber.notify(Unit)
        }
    }

    override fun getAccidental(): Accidental? {
        return configuration.getAccidental()
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
        val event =
            EventFactory().createStaffInteractionData(stage, position, measure, side, action)
        receiver.handleAction(event)
    }

    override fun subscribe(subscriber: Notifiable<Unit>) {
        modeSubscribers.add(subscriber)
    }

    override fun unsubscribe(subscriber: Notifiable<Unit>) {
        modeSubscribers.remove(subscriber)
    }

    override fun synchronize(caller: Notifiable<Unit>) {
        caller.notify(Unit)
    }
}
