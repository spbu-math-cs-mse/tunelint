package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.view.GeometryData

@Composable
fun FullHeightBox(
    geometryData: GeometryData,
    steps: Int,
    content: @Composable BoxScope.() -> Unit,
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
