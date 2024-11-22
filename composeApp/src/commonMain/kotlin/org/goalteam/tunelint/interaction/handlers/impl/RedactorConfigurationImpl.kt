package org.goalteam.tunelint.interaction.handlers.impl

import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.interaction.handlers.RedactorConfiguration
import org.goalteam.tunelint.model.core.PrimaryNoteValue

class RedactorConfigurationImpl(
    private var value: PrimaryNoteValue = PrimaryNoteValue.Quarter,
    private var read: Mode = Mode.Read,
) : RedactorConfiguration {
    override fun getValue(): PrimaryNoteValue = value

    override fun setValue(value: PrimaryNoteValue) {
        this.value = value
    }

    override fun getMode(): Mode = read

    override fun setMode(mode: Mode) {
        read = mode
    }
}
