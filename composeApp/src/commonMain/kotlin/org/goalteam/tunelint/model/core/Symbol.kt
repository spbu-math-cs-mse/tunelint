package org.goalteam.tunelint.model.core

interface Symbol : Cloneable {
    public override fun clone(): Symbol

    fun value(): NoteValue

    fun primaryValue(): PrimaryNoteValue
}
