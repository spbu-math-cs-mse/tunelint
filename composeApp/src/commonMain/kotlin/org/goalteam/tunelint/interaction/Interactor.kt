package org.goalteam.tunelint.interaction

import org.goalteam.tunelint.interaction.events.Action
import org.goalteam.tunelint.interaction.events.CommandType
import org.goalteam.tunelint.interaction.events.Mode
import org.goalteam.tunelint.interaction.events.Side
import org.goalteam.tunelint.model.changerequest.Subscribable
import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.PrimaryNoteValue

interface Interactor : Subscribable<Unit> {
    fun setValue(value: PrimaryNoteValue)

    fun getValue(): PrimaryNoteValue

    fun setMode(mode: Mode)

    fun getMode(): Mode

    fun setAccidental(accidental: Accidental?)

    fun getAccidental(): Accidental?

    fun handleButton(command: CommandType)

    fun handleAction(
        stage: NoteOffset,
        position: Int,
        measure: Int,
        side: Side,
        action: Action,
    )
}

data class CurrentMode(
    val mode: Mode,
)
