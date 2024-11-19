package org.goalteam.tunelint.view.viewable.impl

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.goalteam.tunelint.model.core.ImmutableMeasure
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.Note
import org.goalteam.tunelint.model.core.Rest
import org.goalteam.tunelint.model.core.Symbol
import org.goalteam.tunelint.model.core.TimeSignature
import org.goalteam.tunelint.view.GeometryData
import org.goalteam.tunelint.view.composable.MeasureView
import org.goalteam.tunelint.view.viewable.ImmutableMeasureViewable
import org.goalteam.tunelint.view.viewable.MeasureViewable
import org.goalteam.tunelint.view.viewable.SymbolViewable

class MeasureViewableImpl(
    private val measure: Measure,
) : ImmutableMeasure by measure,
    MeasureViewable {
    override var snapshot: ImmutableMeasureViewable
        by mutableStateOf(ImmutableMeasureViewableImpl(this))

    @Composable
    override fun view() =
        MeasureView(
            snapshot,
            GeometryData(20, 20, 50, 50),
        )

    override fun clone() = MeasureViewableImpl(measure.clone())

    override fun addSymbol(
        position: Int,
        symbol: Symbol,
    ) {
        measure.addSymbol(position, viewableSymbolOf(symbol))
        takeSnapshot()
    }

    override fun removeSymbol(position: Int) {
        measure.removeSymbol(position)
        takeSnapshot()
    }

    override fun changeSymbol(
        position: Int,
        symbol: Symbol,
    ) {
        measure.changeSymbol(position, viewableSymbolOf(symbol))
        takeSnapshot()
    }

    override fun setSymbols(symbolCollection: Collection<Symbol>) {
        val decorated = mutableListOf<SymbolViewable>()
        symbolCollection.forEach { decorated.add(viewableSymbolOf(it)) }
        measure.setSymbols(decorated)
        takeSnapshot()
    }

    override fun setTimeSignature(timeSignature: TimeSignature) {
        measure.setTimeSignature(timeSignature)
        takeSnapshot()
    }

    private fun takeSnapshot() {
        snapshot = ImmutableMeasureViewableImpl(this)
    }
}

fun viewableSymbolOf(symbol: Symbol): SymbolViewable =
    when (symbol) {
        is Note -> NoteViewableImpl(symbol)
        is Rest -> RestViewableImpl(symbol)

        else -> throw Exception("unknown symbol")
    }
