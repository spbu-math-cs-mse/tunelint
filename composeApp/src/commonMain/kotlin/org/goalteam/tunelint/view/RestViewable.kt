package org.goalteam.tunelint.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.goalteam.tunelint.model.core.Rest

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
                        geometryData.horizontalStep * rest.value().toInt(),
                    height = geometryData.fullHeight,
                ),
        // .border(
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

class RestViewable(
    rest: Rest,
) : SymbolViewable,
    Rest by rest {
    @Composable
    override fun view() =
        RestView(
            this,
            GeometryData(20, 20, 50, 50),
        )
}
