package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.view.GeometryData
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.note_flag

@Composable
fun BottomNoteFlag(
    geometryData: GeometryData,
    index: Int,
) {
    Box {
        Image(
            painter = painterResource(Res.drawable.note_flag),
            contentDescription = null,
            modifier =
                Modifier
                    .rotate(180.0F)
                    .requiredSize(
                        height = geometryData.verticalStep * 3,
                        width = geometryData.verticalStep * 2,
                    ).offset(
                        x = 1.1 * geometryData.verticalStep,
                        y = 0.2 * geometryData.verticalStep,
                    ).align(
                        Alignment.CenterEnd,
                    ),
        )
    }
}
