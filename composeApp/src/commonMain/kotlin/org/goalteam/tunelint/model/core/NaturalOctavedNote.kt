package org.goalteam.tunelint.model.core

class NaturalOctavedNote(
    private val value: Int,
) {
    fun octave() = value / Constants.OCTAVE_NOTES

    fun naturalNote() = NaturalNote(value % Constants.OCTAVE_NOTES)

    fun pitch() = naturalNote().pitchOffset() + octave() * Constants.OCTAVE_SEMITONES

    operator fun minus(other: NaturalOctavedNote) = NoteOffset(value - other.value)

    operator fun plus(other: NoteOffset) = NaturalOctavedNote(value + other.value)
}
