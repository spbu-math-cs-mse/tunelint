package org.goalteam.tunelint.interaction.handlers.impl

import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.interaction.handlers.RedactorConfiguration
import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.model.core.PrimaryNoteValue

class RedactorConfigurationImpl(
    private var value: PrimaryNoteValue = PrimaryNoteValue.Quarter,
    private var mode: Mode = Mode.AddNote,
    private var accidental: Accidental? = null,
) : RedactorConfiguration {
    override fun getValue(): PrimaryNoteValue = value

    override fun setValue(value: PrimaryNoteValue) {
        this.value = value
    }

    override fun getMode(): Mode = mode

    override fun setMode(mode: Mode) {
        this.mode = mode
    }

    override fun getAccidental(): Accidental? {
        return accidental
    }

    override fun setAccidental(accidental: Accidental?) {
        this.accidental = accidental
    }
}
