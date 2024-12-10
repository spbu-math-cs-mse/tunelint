package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.core.ImmutableMelody

internal class OddMeasureCount(
    private val melody: ImmutableMelody,
) : Rule {
    override fun check(): Status =
        if (melody.measures.size % 2 == 1) {
            Ok("Measures are OK")
        } else {
            Error(melody.measures, "Measure count must be odd")
        }
}
