package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.view.GeometryData
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.note_stem

@Composable
fun TopNoteStem(geometryData: GeometryData) {
    Box {
        Image(
            painter = painterResource(Res.drawable.note_stem),
            contentDescription = null,
            modifier =
                Modifier
                    .requiredSize(
                        height = geometryData.verticalStep * 3,
                        width = geometryData.verticalStep * 3,
                    ).offset(
                        x = geometryData.verticalStep / 2,
                        y = -1.6 * geometryData.verticalStep,
                    ).align(
                        Alignment.CenterEnd,
                    ),
        )
    }
}
