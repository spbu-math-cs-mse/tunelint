package org.goalteam.tunelint.interaction.handlers

import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.interaction.handlers.impl.ReceiverImpl
import org.goalteam.tunelint.interaction.handlers.impl.RedactorConfigurationImpl
import org.goalteam.tunelint.model.changerequest.PersistenceManager
import org.goalteam.tunelint.model.core.PrimaryNoteValue

class InteractionHandlerFactory {
    fun createConfiguration(
        defaultValue: PrimaryNoteValue = PrimaryNoteValue.Quarter,
        defaultMode: Mode = Mode.AddNote,
    ): RedactorConfiguration = RedactorConfigurationImpl(defaultValue, defaultMode)

    fun createReceiver(
        configuration: RedactorConfiguration,
        manager: PersistenceManager,
    ): Receiver = ReceiverImpl(configuration, manager)
}
