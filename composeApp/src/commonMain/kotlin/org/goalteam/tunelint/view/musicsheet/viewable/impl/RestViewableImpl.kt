package org.goalteam.tunelint.view.musicsheet.viewable.impl

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.Rest
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.composable.RestView
import org.goalteam.tunelint.view.musicsheet.viewable.RestViewable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel

class RestViewableImpl(
    rest: Rest,
) : RestViewable,
    Rest by rest {
    @Composable
    override fun view(
        vm: RedactorScreenViewModel,
        position: Int,
        measure: Int,
        geometryData: InternalGeometryData,
    ) = RestView(
        vm,
        position,
        measure,
        this,
        geometryData,
    )

    override fun stepsBeforeMiddle() = 1

    override fun stepsAfterMiddle() = 1
}
