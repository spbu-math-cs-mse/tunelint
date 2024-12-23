package org.goalteam.tunelint.model.changerequest.impl

import org.goalteam.tunelint.model.changerequest.ChangeRequest
import org.goalteam.tunelint.model.core.Accidental
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.MusicFactory
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.NotePointer

class ChangeAccidentalRequest(
    private val notePointer: NotePointer,
    private val accidental: Accidental?,
) : ChangeRequest {
    private var _accidental: Accidental? = null
    val oldAccidental get() = _accidental
    override fun toString(): String = "change accidental to $accidental on position $notePointer}"

    override fun execute(melody: Melody): Boolean {

        var note: Note? = null
        melody.mutateMeasures { mutableMeasures ->
            val symbol =
                mutableMeasures[notePointer.measure(melody)]
                    .symbols
                    .getOrNull(notePointer.position(melody))
                    ?.clone()
            if (symbol is Note) {
                _accidental = symbol.accidental()
                note = symbol
            }
        }

        if(note == null)
            return false

        var success = false

        melody.mutateMeasures { mutableMeasures: List<Measure> ->
            success =
                mutableMeasures[notePointer.measure(melody)]
                    .changeSymbol(
                        notePointer.position(melody),
                        MusicFactory().createNoteWithAccidental(note!!, accidental)
                    )
        }
        return success
    }

}