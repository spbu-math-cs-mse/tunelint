package org.goalteam.tunelint.model.core

interface NotePointer {
    fun measure(melody: Melody): Int

    fun position(melody: Melody): Int

    fun next(): NotePointer

    override fun toString(): String
}
