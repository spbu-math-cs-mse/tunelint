package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.staffHeight
import org.goalteam.tunelint.view.musicsheet.viewable.RestViewable
import org.goalteam.tunelint.view.musicsheet.viewable.horizontalSteps
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.quarterrest

@Composable
fun RestView(
    rest: RestViewable,
    geometryData: InternalGeometryData,
) {
    FullHeightBox(geometryData, rest.horizontalSteps()) {
        Image(
            painter = painterResource(Res.drawable.quarterrest),
            contentDescription = null,
            modifier =
                Modifier
                    .offset(y = geometryData.topMargin)
                    .size(height = geometryData.staffHeight, width = geometryData.horizontalStep * 2)
                    .align(Alignment.TopCenter),
        )
    }
}
