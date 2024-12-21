package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.timeSignatureWidth
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMelodyViewable
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.music0
import tunelint.composeapp.generated.resources.music1
import tunelint.composeapp.generated.resources.music2
import tunelint.composeapp.generated.resources.music3
import tunelint.composeapp.generated.resources.music4
import tunelint.composeapp.generated.resources.music5
import tunelint.composeapp.generated.resources.music6
import tunelint.composeapp.generated.resources.music7
import tunelint.composeapp.generated.resources.music8
import tunelint.composeapp.generated.resources.music9

@Composable
fun TimeSignatureFontNumber(number: Int) {
    val symbols =
        mapOf(
            '0' to Res.drawable.music0,
            '1' to Res.drawable.music1,
            '2' to Res.drawable.music2,
            '3' to Res.drawable.music3,
            '4' to Res.drawable.music4,
            '5' to Res.drawable.music5,
            '6' to Res.drawable.music6,
            '7' to Res.drawable.music7,
            '8' to Res.drawable.music8,
            '9' to Res.drawable.music9,
        )

    Row {
        number
            .toString()
            .forEach {
                Image(
                    painter = painterResource(symbols[it]!!),
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight,
                )
            }
    }
}

@Composable
fun TimeSignatureView(
    melody: ImmutableMelodyViewable,
    geometryData: InternalGeometryData,
) {
    Box(modifier = Modifier.width(geometryData.timeSignatureWidth)) {
        Box(
            modifier =
                Modifier
                    .height(geometryData.verticalStep * 2)
                    .offset(y = geometryData.topMargin + 0.5 * geometryData.verticalStep)
                    .padding(vertical = 0.1 * geometryData.verticalStep),
        ) { TimeSignatureFontNumber(melody.timeSignature.count) }
        Box(
            modifier =
                Modifier
                    .height(geometryData.verticalStep * 2)
                    .offset(y = geometryData.topMargin + 2.5 * geometryData.verticalStep)
                    .padding(vertical = 0.1 * geometryData.verticalStep),
        ) { TimeSignatureFontNumber(melody.timeSignature.primary.denominator()) }
    }
}
