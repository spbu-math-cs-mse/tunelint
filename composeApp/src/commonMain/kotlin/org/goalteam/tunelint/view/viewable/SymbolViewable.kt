package org.goalteam.tunelint.view.viewable

import org.goalteam.tunelint.model.core.Symbol

interface SymbolViewable :
    Symbol,
    Viewable {
    fun horizontalSteps(): Int
}
