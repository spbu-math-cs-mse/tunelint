package org.goalteam.tunelint.model.musicsheetchangerequest

import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.musicsheetchangeinfo.MusicSheetChangeInfo

interface MusicSheetChangeRequest {
    override fun toString(): String

    fun execute(linkedList: Collection<Note>): MusicSheetChangeInfo

    fun revert(linkedList: Collection<Note>): MusicSheetChangeInfo
}
