package org.goalteam.tunelint.model.core

import org.goalteam.tunelint.model.core.impl.MeasureImpl
import org.goalteam.tunelint.model.core.impl.NoteImpl
import kotlin.test.Test
import kotlin.test.assertEquals

class MeasureTests {
    @Test
    fun `addition success`() {
        val measure = MeasureImpl(TimeSignature.standardTime, listOf())
        val symbol = NoteImpl(0, PrimaryNoteValue(0))

        measure.addSymbol(0, symbol)
        val expected =
            MeasureImpl(
                TimeSignature.standardTime,
                listOf(NoteImpl(0, PrimaryNoteValue(0))),
            )

        assertEquals(expected, measure, "should be equal")
    }

    @Test
    fun `addition fail`() {
        val measure = MeasureImpl(TimeSignature.standardTime, listOf())
        val symbol = NoteImpl(0, PrimaryNoteValue(2))

        measure.addSymbol(0, symbol)
        val expected = MeasureImpl(TimeSignature.standardTime, listOf())

        assertEquals(expected, measure, "should be equal")
    }
}
