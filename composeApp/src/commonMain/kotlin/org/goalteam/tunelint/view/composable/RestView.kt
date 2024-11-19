package org.goalteam.tunelint.view.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.model.core.Rest
import org.goalteam.tunelint.view.GeometryData

@Composable
fun RestView(
    rest: Rest,
    geometryData: GeometryData,
) {
    Box(
        modifier =
            Modifier
                .size(
                    width =
                        geometryData.horizontalStep * (rest.value() / PrimaryNoteValue.Eighth.value()),
                    height = geometryData.fullHeight,
                ),
        // .border( TODO REMOVE COMMENTED
        //    width = 2.dp,
        //    color = Color.Black,
        //    shape = RectangleShape,
        // ),
    ) {
        Box(
            modifier =
                Modifier
                    .size(
                        width = geometryData.horizontalStep,
                        height = geometryData.verticalStep * 2,
                    ).background(
                        Color.Blue,
                    ).align(
                        Alignment.Center,
                    ),
        )
    }
}
