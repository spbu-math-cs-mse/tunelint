package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.view.musicsheet.Constants
import org.goalteam.tunelint.view.musicsheet.ExternalGeometryData
import org.goalteam.tunelint.view.musicsheet.staffWidth

@Composable
fun StaffLine(
    geometryData: ExternalGeometryData,
    width: Dp,
) {
    Box(
        modifier =
            Modifier
                .padding(vertical = 0.5 * (geometryData.verticalStep - width))
                .size(width = geometryData.staffWidth, height = width)
                .background(Color.Black),
    )
}

@Composable
fun Staff(
    geometryData: ExternalGeometryData,
    width: Dp,
) {
    Box(
        modifier =
            Modifier
                .offset(y = geometryData.topMargin),
    ) {
        for (i in 0..<Constants.STAFF_LINES) {
            Box(
                modifier =
                    Modifier
                        .offset(y = i * geometryData.verticalStep),
            ) {
                StaffLine(geometryData, width)
            }
        }
    }
}
