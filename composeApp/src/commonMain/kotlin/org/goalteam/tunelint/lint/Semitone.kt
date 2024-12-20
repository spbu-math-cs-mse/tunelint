package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Note
import kotlin.math.abs

class Semitone(
    val melody: ImmutableMelody,
) : Rule {
    override fun check(): Status {
        val containsSemitone =
            melody
                .measures
                .map {
                    it
                        .symbols
                        .filterIsInstance<Note>()
                        .map { note -> (melody.clef.bottomLineNote() + note.stage()).pitch() }
                        .sorted()
                }.any {
                    it
                        .zip(it.drop(1))
                        .any { pair -> abs(pair.second - pair.first) == 1 }
                }

        if (containsSemitone) {
            return Error(
                listOf(),
                "Contains sounds, differing by semitone, inside a single measure",
            )
        }

        return Ok("Semitones in measures OK")
    }
}
