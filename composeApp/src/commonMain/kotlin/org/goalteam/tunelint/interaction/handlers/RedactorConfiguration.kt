package org.goalteam.tunelint.interaction.handlers

import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.model.core.PrimaryNoteValue

/**
 * Storage for current redactor state.
 * Use this via Interactor not just by itself
 */
interface RedactorConfiguration {
    fun getValue(): PrimaryNoteValue

    fun setValue(value: PrimaryNoteValue)

    fun getMode(): Mode

    fun setMode(mode: Mode)
}
