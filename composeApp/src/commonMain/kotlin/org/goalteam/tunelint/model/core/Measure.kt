package org.goalteam.tunelint.model.core

interface Measure :
    ImmutableMeasure,
    MutableMeasure,
    Cloneable {
    public override fun clone(): Measure
}

fun Measure.pushBackSymbol(symbol: Symbol) = addSymbol(symbols.count(), symbol)

fun Measure.fillRemainingWithPauses() {
    var currentValue = PrimaryNoteValue.Double

    while (remainingValue > NoteValue.Nil) {
        if (remainingValue >= currentValue.value()) {
            pushBackSymbol(MusicFactory().createRest(currentValue))
        } else {
            currentValue = currentValue.prev()
        }
    }
}
