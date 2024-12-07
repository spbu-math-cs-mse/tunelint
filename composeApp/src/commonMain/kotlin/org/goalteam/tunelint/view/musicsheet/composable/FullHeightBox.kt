package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.fullHeight

@Composable
fun FullHeightBox(
    geometryData: InternalGeometryData,
    steps: Int,
    content: @Composable BoxScope.() -> Unit = {},
) {
    Box(
        modifier =
            Modifier
                .size(
                    height = geometryData.fullHeight,
                    width = geometryData.horizontalStep * steps,
                ),
        content = content,
    )
}
