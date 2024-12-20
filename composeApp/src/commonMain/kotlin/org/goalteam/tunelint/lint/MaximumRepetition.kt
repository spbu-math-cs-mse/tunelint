package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.symbols

class MaximumRepetition(
    val melody: ImmutableMelody,
) : Rule {
    override fun check(): Status {
        val maximum = melody.symbols().filterIsInstance<Note>().maxByOrNull { it.stage() }

        if (maximum == null) {
            return Ok("Maximal sound not repeated")
        }

        val maximums =
            melody
                .symbols()
                .filterIsInstance<Note>()
                .filter { it.stage() == maximum.stage() }

        if (maximums.count() > 1) {
            return Error(maximums, "Maximal sound should not be repeated")
        }

        return Ok("Maximal sound not repeated")
    }
}
