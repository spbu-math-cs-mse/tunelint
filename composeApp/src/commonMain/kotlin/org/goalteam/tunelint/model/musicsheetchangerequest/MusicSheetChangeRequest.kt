package org.goalteam.tunelint.model.musicsheetchangerequest

import org.goalteam.tunelint.model.core.Note

interface MusicSheetChangeRequest {
    override fun toString(): String

    fun execute(list: MutableList<Note>): MutableList<Note>

    fun revert(list: MutableList<Note>): MutableList<Note>
}
