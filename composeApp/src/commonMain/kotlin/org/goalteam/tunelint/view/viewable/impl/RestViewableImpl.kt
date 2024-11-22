package org.goalteam.tunelint.view.viewable.impl

import androidx.compose.runtime.Composable
import org.goalteam.tunelint.model.core.Rest
import org.goalteam.tunelint.view.GeometryData
import org.goalteam.tunelint.view.composable.RestView
import org.goalteam.tunelint.view.viewable.RestViewable

class RestViewableImpl(
    rest: Rest,
) : RestViewable,
    Rest by rest {
    @Composable
    override fun view() =
        RestView(
            this,
            GeometryData(20, 20, 50, 50),
        )
}
