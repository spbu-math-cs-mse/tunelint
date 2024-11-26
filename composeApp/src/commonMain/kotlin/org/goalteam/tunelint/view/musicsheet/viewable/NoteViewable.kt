package org.goalteam.tunelint.view.musicsheet.viewable

import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.PrimaryNoteValue

interface NoteViewable :
    Note,
    SymbolViewable

fun NoteViewable.isStemDown() = stage().value > 3

fun NoteViewable.hasFlag() = primaryValue() <= PrimaryNoteValue.Eighth

fun NoteViewable.hasStem() = primaryValue() <= PrimaryNoteValue.Half

fun NoteViewable.flags(): Int {
    if (!hasFlag()) return 0

    val primaryNoteValue = primaryValue()
    var flagsCount = 0

    while (primaryNoteValue <= PrimaryNoteValue.Eighth) {
        flagsCount += 1
    }

    return flagsCount
}
