package org.goalteam.tunelint.interaction

import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.CommandType
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.interaction.events.Side
import org.goalteam.tunelint.model.changerequest.Subscribable
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.PrimaryNoteValue

interface Interactor : Subscribable<CurrentMode> {
    fun setValue(value: PrimaryNoteValue)

    fun setMode(mode: Mode)

    fun handleButton(command: CommandType)

    fun handleAction(
        stage: NoteOffset,
        position: Int,
        measure: Int,
        side: Side,
        action: Action,
    )
}

data class CurrentMode(val mode: Mode)