package org.goalteam.tunelint.model.core

class NaturalOctavedNote(
    val note: NaturalNote,
    val octave: Int,
) {
    constructor(value: Int) :
        this(NaturalNote(value % Constants.OCTAVE_NOTES), value / Constants.OCTAVE_NOTES)

    fun value() = octave * Constants.OCTAVE_NOTES + note.value

    fun pitch() = note.pitchOffset() + octave * Constants.OCTAVE_SEMITONES

    operator fun minus(other: NaturalOctavedNote) = NoteOffset(value() - other.value())

    operator fun plus(other: NoteOffset) = NaturalOctavedNote(value() + other.value)
}
