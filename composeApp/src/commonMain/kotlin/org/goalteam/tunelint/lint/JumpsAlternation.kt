package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Note
import kotlin.math.sign

class JumpsAlternation(
    private val melody: ImmutableMelody,
) : Rule {
    override fun check(): Status {
        val errors =
            triples()
                .filter(this::sameDirection)
                .map {
                    Error(it.toList(), "No jumps in one direction are allowed")
                }
        return if (errors.isEmpty()) {
            Ok("Jumps are OK")
        } else {
            Error(errors)
        }
    }

    private fun sameDirection(triple: Triple<Note, Note, Note>): Boolean {
        val first = triple.first.value()
        val second = triple.second.value()
        val third = triple.third.value()
        return first.compareTo(second).sign == second.compareTo(third).sign
    }

    private fun triples(): List<Triple<Note, Note, Note>> {
        val notes: Sequence<Note> =
            melody.measures
                .asSequence()
                .flatMap { it.symbols.filterIsInstance<Note>() }
        return notes
            .zip(notes.drop(1))
            .zip(notes.drop(2))
            .map { (pair, note) -> Triple(note, pair.second, pair.first) }
            .toList()
    }
}
