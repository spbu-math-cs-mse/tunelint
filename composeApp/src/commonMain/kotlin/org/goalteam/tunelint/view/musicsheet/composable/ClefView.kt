package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.model.core.Clef
import org.goalteam.tunelint.view.musicsheet.*
import org.goalteam.tunelint.view.musicsheet.viewable.ImmutableMelodyViewable
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.c_clef
import tunelint.composeapp.generated.resources.f_clef
import tunelint.composeapp.generated.resources.g_clef

@Composable
fun GClefView(geometryData: InternalGeometryData) {
    Box(
        modifier =
            Modifier
                .padding(top = geometryData.topMargin)
                .height(geometryData.staffHeight),
    ) {
        Image(
            painter = painterResource(Res.drawable.g_clef),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier =
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart),
        )
    }
}

@Composable
fun CClefView(geometryData: InternalGeometryData) {
    Box(
        modifier =
            Modifier
                .padding(top = 0.5 * geometryData.verticalStep + geometryData.topMargin)
                .height(geometryData.staffShortHeight),
    ) {
        Image(
            painter = painterResource(Res.drawable.c_clef),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier =
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart),
        )
    }
}

@Composable
fun FClefView(geometryData: InternalGeometryData) {
    Box(
        modifier =
            Modifier
                .padding(top = geometryData.topMargin + 0.5 * geometryData.verticalStep)
                .height(geometryData.staffShortHeight - geometryData.verticalStep),
    ) {
        Image(
            painter = painterResource(Res.drawable.f_clef),
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier =
                Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterStart),
        )
    }
}

@Composable
fun ClefView(
    vm: RedactorScreenViewModel,
    melody: ImmutableMelodyViewable,
    geometryData: InternalGeometryData,
) {
    Box(
        modifier =
            Modifier.width(geometryData.clefWidth).pointerInput(Unit) {
                detectTapGestures(onLongPress = { offset: Offset ->
                    vm.interactor.changeClef(melody.clef)
                })
            },
    ) {
        when (melody.clef.type) {
            Clef.ClefType.G -> GClefView(geometryData)
            Clef.ClefType.C -> CClefView(geometryData)
            Clef.ClefType.F -> FClefView(geometryData)
        }
    }
}
