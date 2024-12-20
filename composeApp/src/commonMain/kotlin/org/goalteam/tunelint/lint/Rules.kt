package org.goalteam.tunelint.lint

import org.goalteam.tunelint.model.core.ImmutableMelody

class Rules(
    private val melody: ImmutableMelody,
) {
    fun all(): List<Rule> =
        listOf(
            OddMeasureCount(melody),
            JumpsAlternation(melody),
            MelodyRange(melody),
            PausesNumber(melody),
            TimeSignatureConstraint(melody),
            MinimumRepetition(melody),
            MaximumRepetition(melody),
        )
}
