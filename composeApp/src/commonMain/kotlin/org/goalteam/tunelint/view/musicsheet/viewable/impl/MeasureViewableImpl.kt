package org.goalteam.tunelint.view.musicsheet.viewable.impl

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
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.composable.MeasureView
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMeasureViewable
import org.goalteam.tunelint.view.musicsheet.viewable.MeasureViewable
import org.goalteam.tunelint.view.musicsheet.viewable.SymbolViewable
import org.goalteam.tunelint.view.musicsheet.viewable.horizontalSteps
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

class MeasureViewableImpl(
    private val measure: Measure,
) : ImmutableMeasure by measure,
    MeasureViewable {
    override var snapshot: ImmutableMeasureViewable
        by mutableStateOf(ImmutableMeasureViewableImpl(this))

    init {
        setSymbols(measure.symbols)
    }

    @Composable
    override fun view(
        vm: RedactorScreenViewModel,
        index: Int,
        geometryData: InternalGeometryData,
    ) = MeasureView(
        vm,
        index,
        snapshot,
        geometryData,
    )

    override fun delegate(): Measure = measure

    override fun horizontalSteps() = symbols.sumOf { (it as SymbolViewable).horizontalSteps() }

    override fun clone() = MeasureViewableImpl(measure.clone())

    override fun addSymbol(
        position: Int,
        symbol: Symbol,
    ): Boolean {
        val success = measure.addSymbol(position, viewableSymbolOf(symbol))
        takeSnapshot()
        return success
    }

    override fun removeSymbol(position: Int): Boolean {
        val success = measure.removeSymbol(position)
        takeSnapshot()
        return success
    }

    override fun changeSymbol(
        position: Int,
        symbol: Symbol,
    ) {
        measure.changeSymbol(position, viewableSymbolOf(symbol))
        takeSnapshot()
    }

    override fun setSymbols(symbolCollection: Collection<Symbol>) {
        measure.setSymbols(symbolCollection.map { viewableSymbolOf(it) })
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
