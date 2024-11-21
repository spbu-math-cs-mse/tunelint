package org.goalteam.tunelint.interaction

import org.goalteam.tunelint.model.core.PrimaryNoteValue

interface RedactorConfiguration {
    fun getValue(): PrimaryNoteValue

    fun setValue(value: PrimaryNoteValue)

    /**
     true if we are deleting content*/
    fun getMode(): Boolean

    fun setMode(delete: Boolean)
}
