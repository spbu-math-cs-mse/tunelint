package org.goalteam.tunelint.model.core.impl

import org.goalteam.tunelint.model.core.ImmutableMeasure
import org.goalteam.tunelint.model.core.Measure
import org.goalteam.tunelint.model.core.NoteValue
import org.goalteam.tunelint.model.core.Symbol
import org.goalteam.tunelint.model.core.TimeSignature

class MeasureImpl(
    timeSignature: TimeSignature,
    symbols: Collection<Symbol>,
) : Measure {
    private val _symbols = symbols.toMutableList()
    private var _timeSignature = timeSignature
    private var _remainingValue: NoteValue

    init {
        var occupiedValue = totalSymbolsValue()

        while (occupiedValue > timeSignature.value()) {
            occupiedValue -= _symbols.removeLast().value()
        }

        _remainingValue = timeSignature.value() - occupiedValue
    }

    override val symbols = _symbols as List<Symbol>
    override val remainingValue = _remainingValue
    override val timeSignature = _timeSignature

    override fun addSymbol(
        position: Int,
        symbol: Symbol,
    ) {
        if (symbol.value() > remainingValue) {
            return
        }

        _symbols.add(position, symbol)
        _remainingValue -= symbol.value()
    }

    override fun removeSymbol(position: Int) {
        val removed = _symbols.removeAt(position)
        _remainingValue += removed.value()
    }

    override fun changeSymbol(
        position: Int,
        symbol: Symbol,
    ) {
        if (remainingValue >= symbol.value() - symbols[position].value()) {
            _symbols[position] = symbol
            _remainingValue -= symbol.value() - symbols[position].value()
        }
    }

    override fun setSymbols(symbolCollection: Collection<Symbol>) {
        _symbols.clear()
        _symbols.addAll(symbolCollection)
        _remainingValue = timeSignature.value() - totalSymbolsValue()
    }

    override fun setTimeSignature(timeSignature: TimeSignature) {
        var occupiedValue = this.timeSignature.value() - remainingValue

        _timeSignature = timeSignature

        while (occupiedValue > timeSignature.value()) {
            occupiedValue -= _symbols.removeLast().value()
        }

        _remainingValue = timeSignature.value() - occupiedValue
    }

    override fun clone() = MeasureImpl(timeSignature, symbols)

    override fun equals(other: Any?) =
        other != null &&
            other is ImmutableMeasure &&
            other.symbols.count() == _symbols.count() &&
            _symbols.foldIndexed(true) { index, acc, symbol ->
                acc && (other.symbols[index] == symbol)
            }

    override fun hashCode() = _symbols.fold(0) { acc, it -> acc xor it.hashCode() }

    private fun totalSymbolsValue() = _symbols.fold(NoteValue.Nil) { acc, it -> acc + it.value() }
}
