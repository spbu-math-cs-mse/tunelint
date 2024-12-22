package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.lint.status.Warning
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Rest
import org.goalteam.tunelint.model.core.symbols

class PausesNumber(
    val melody: ImmutableMelody,
) : Rule {
    object Constants {
        const val SOFT_CAP = 3
        const val HARD_CAP = 5
    }

    override fun check(): Status {
        val rests = melody.symbols().filterIsInstance<Rest>()

        if (rests.count() > Constants.HARD_CAP) {
            return Error(
                rests,
                "Too many rests (${rests.count()}) " +
                    "while must not be more than ${Constants.SOFT_CAP}",
            )
        }

        if (rests.count() > Constants.SOFT_CAP) {
            return Warning(
                rests,
                "Too many rests (${rests.count()}) " +
                    "while must not be more than ${Constants.SOFT_CAP}",
            )
        }

        return Ok("Okay number of rests")
    }
}
