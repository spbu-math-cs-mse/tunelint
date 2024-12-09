package org.goalteam.tunelint.view.musicsheet.viewable

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.Symbol
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

interface SymbolViewable : Symbol {
    @Composable
    fun view(
        vm: RedactorScreenViewModel,
        position: Int,
        measure: Int,
        geometryData: InternalGeometryData,
    )

    fun stepsBeforeMiddle(): Int

    fun stepsAfterMiddle(): Int
}

fun SymbolViewable.horizontalSteps() = stepsBeforeMiddle() + stepsAfterMiddle()
