package org.goalteam.tunelint.model.musicsheetchangerequest

import org.goalteam.tunelint.model.core.Melody
import org.goalteam.tunelint.model.core.Symbol

class AddSymbolRequest(
    private val measure: Int,
    private val note: Int,
    private val symbol: Symbol,
    private val sheet: Melody,
) : MusicSheetChangeRequest {
    override fun toString(): String = "add symbol $symbol at $note in $measure measure"

    override fun execute() {
        symbols().add(note, symbol)
    }

    override fun isExecutable(): Boolean = measures().size > measure && symbols().size > note

    override fun revert() {
        symbols().removeAt(note)
    }

    override fun isRevertible(): Boolean =
        isExecutable() &&
            symbols()[note] == symbol

    private fun symbols() =
        measures()[measure]
            .symbols()

    private fun measures() =
        sheet
            .contents()
}
