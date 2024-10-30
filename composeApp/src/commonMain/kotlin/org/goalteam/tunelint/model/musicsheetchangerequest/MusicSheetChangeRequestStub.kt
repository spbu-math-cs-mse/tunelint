package org.goalteam.tunelint.model.musicsheetchangerequest

import org.goalteam.tunelint.model.core.Note

class MusicSheetChangeRequestStub(
    private val note: Note,
) : MusicSheetChangeRequest {
    override fun toString() = note.toString()

    override fun execute(list: MutableList<Note>): MutableList<Note> {
        list.add(note)
        return list
    }

    override fun revert(list: MutableList<Note>): MutableList<Note> {
        list.removeLast()
        return list
    }
}
