package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.symbols

class MinimumRepetition(
    val melody: ImmutableMelody,
) : Rule {
    override fun check(): Status {
        val minimum = melody.symbols().filterIsInstance<Note>().minByOrNull { it.stage() }

        if (minimum == null) {
            return Ok("Minimal sound not repeated")
        }

        val minimums =
            melody
                .symbols()
                .filterIsInstance<Note>()
                .filter { it.stage() == minimum.stage() }

        if (minimums.count() > 1) {
            return Error(minimums, "Minimal sound should not be repeated")
        }

        return Ok("Minimal sound not repeated")
    }
}
