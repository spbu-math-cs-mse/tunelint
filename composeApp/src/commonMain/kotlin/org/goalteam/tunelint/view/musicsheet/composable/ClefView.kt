package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.g_clef

@Composable
fun ClefView(geometryData: InternalGeometryData) {
    FullHeightBox(geometryData, 2) {
        Image(
            painter = painterResource(Res.drawable.g_clef),
            contentDescription = null,
            modifier =
                Modifier
                    .padding(top = geometryData.topMargin, bottom = geometryData.bottomMargin)
                    .fillMaxSize(),
        )
    }
}
