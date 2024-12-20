package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.NoteOffset
import org.goalteam.tunelint.model.core.symbols

class MelodyRange(
    private val melody: ImmutableMelody,
) : Rule {
    object Constants {
        const val DUODECIMA = 20
    }

    override fun check(): Status {
        val maximum =
            melody.clef.bottomLineNote() + (
                melody
                    .symbols()
                    .filterIsInstance<Note>()
                    .maxOfOrNull { it.stage() } ?: NoteOffset(0)
            )
        val minimum =
            melody.clef.bottomLineNote() + (
                melody
                    .symbols()
                    .filterIsInstance<Note>()
                    .minOfOrNull { it.stage() } ?: NoteOffset(0)
            )

        val difference = maximum.pitch() - minimum.pitch()

        if (difference <= Constants.DUODECIMA) {
            return Ok(
                "Difference is $difference semitones, that is no more than" +
                    " duodecima (${Constants.DUODECIMA})",
            )
        }

        return Error(
            listOf(minimum, maximum),
            "difference is $difference, though it" +
                "must not be more than duodecima (${Constants.DUODECIMA})",
        )
    }
}
