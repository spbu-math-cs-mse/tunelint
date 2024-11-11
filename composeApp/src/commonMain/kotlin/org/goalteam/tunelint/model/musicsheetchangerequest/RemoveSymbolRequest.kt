package org.goalteam.tunelint.model.musicsheetchangerequest

import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.MusicSheet
import org.goalteam.tunelint.model.core.Symbol

class RemoveSymbolRequest(
    private val measure: Int,
    private val note: Int,
    private val sheet: MusicSheet,
) : MusicSheetChangeRequest {
    private var removed: Symbol? = null

    override fun toString(): String = "remove note at $note in $measure measure"

    override fun execute() {
        removed = symbols().removeAt(note)
    }

    override fun isExecutable(): Boolean = measures().size > measure && symbols().size > note

    override fun revert() {
        symbols().add(note, removed!!)
    }

    override fun isRevertible(): Boolean = removed != null && isExecutable()

    private fun symbols() =
        measures()[measure]
            .symbols()

    private fun measures() =
        sheet
            .contents()
            .filterIsInstance<Measure>()
}
