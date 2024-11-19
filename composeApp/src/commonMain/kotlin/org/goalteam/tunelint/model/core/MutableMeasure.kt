package org.goalteam.tunelint.model.core

interface MutableMeasure {
    fun addSymbol(
        position: Int,
        symbol: Symbol,
    )

    fun removeSymbol(position: Int)

    fun changeSymbol(
        position: Int,
        symbol: Symbol,
    )

    fun setSymbols(symbolCollection: Collection<Symbol>)

    fun setTimeSignature(timeSignature: TimeSignature)
}
