package org.goalteam.tunelint.model.core

class MusicFactory {
    fun createNote(value: Int): Note = NoteImpl(value)
}
