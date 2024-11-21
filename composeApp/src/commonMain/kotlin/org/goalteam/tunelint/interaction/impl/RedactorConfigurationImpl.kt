package org.goalteam.tunelint.interaction.impl

import org.goalteam.tunelint.interaction.RedactorConfiguration
import org.goalteam.tunelint.model.core.PrimaryNoteValue

class RedactorConfigurationImpl : RedactorConfiguration {
    private var value: PrimaryNoteValue = PrimaryNoteValue.Quarter

    private var read: Boolean = false

    override fun getValue(): PrimaryNoteValue = value

    override fun setValue(value: PrimaryNoteValue) {
        this.value = value
    }

    override fun getMode(): Boolean = read

    override fun setMode(delete: Boolean) {
        read = delete
    }
}
