package org.goalteam.tunelint.lint

import org.goalteam.tunelint.lint.status.Error
import org.goalteam.tunelint.lint.status.Ok
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.model.core.ImmutableMelody
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.symbols

class LongJump(
    val melody: ImmutableMelody,
) : Rule {
    object Constants {
        private const val JUMP_MAX = 7

        fun isLongJump(jump: Int) = jump > JUMP_MAX || jump < -JUMP_MAX
    }

    override fun check(): Status {
        val jumps =
            melody
                .symbols()
                .zip(melody.symbols().drop(1))
                .filter {
                    it.first is Note &&
                        it.second is Note &&
                        (
                            Constants.isLongJump(
                                (melody.clef.bottomLineNote() + (it.second as Note).stage()).pitch() -
                                    (melody.clef.bottomLineNote() + (it.first as Note).stage()).pitch(),
                            )
                        )
                }

        if (jumps.isEmpty()) {
            return Ok("No jumps more than the perfect fifth")
        }

        return Error(jumps, "Jumps more, than the perfect fifth")
    }
}
