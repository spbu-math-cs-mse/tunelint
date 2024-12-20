package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.model.core.TimeSignature

class TimeSignatureConstraint(
    val melody: ImmutableMelody,
) : Rule {
    object Constants {
        val POSSIBLE_TIME_SIGNATURES =
            listOf(
                TimeSignature(2, PrimaryNoteValue.Whole),
                TimeSignature(3, PrimaryNoteValue.Whole),
                TimeSignature(4, PrimaryNoteValue.Whole),
                TimeSignature(2, PrimaryNoteValue.Half),
                TimeSignature(3, PrimaryNoteValue.Half),
                TimeSignature(4, PrimaryNoteValue.Half),
            )
    }

    override fun check(): Status {
        if (Constants.POSSIBLE_TIME_SIGNATURES.contains(melody.timeSignature)) {
            return Ok("Time signature okay")
        }

        return Error(
            listOf(melody.timeSignature),
            "Time signature ${melody.timeSignature} is not allowed. " +
                "Allowed time signatures are: ${Constants.POSSIBLE_TIME_SIGNATURES}",
        )
    }
}
