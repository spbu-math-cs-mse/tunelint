package org.goalteam.tunelint.model.core

interface MutableMeasure {
    fun addSymbol(
        position: Int,
        symbol: Symbol,
    ): Boolean

    fun removeSymbol(position: Int): Boolean

    fun changeSymbol(
        position: Int,
        symbol: Symbol,
    ) : Boolean

    fun setSymbols(symbolCollection: Collection<Symbol>)

    fun setTimeSignature(timeSignature: TimeSignature)
}
