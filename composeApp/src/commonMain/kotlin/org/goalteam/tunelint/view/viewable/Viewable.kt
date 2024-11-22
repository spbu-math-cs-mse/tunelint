package org.goalteam.tunelint.view.viewable

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.view.GeometryData

interface Viewable {
    @Composable
    fun view(geometryData: GeometryData)
}
