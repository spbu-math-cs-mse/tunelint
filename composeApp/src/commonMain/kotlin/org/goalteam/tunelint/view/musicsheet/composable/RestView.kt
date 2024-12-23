package org.goalteam.tunelint.view.musicsheet.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.times
import org.goalteam.tunelint.model.core.PrimaryNoteValue
import org.goalteam.tunelint.view.musicsheet.InternalGeometryData
import org.goalteam.tunelint.view.musicsheet.staffHeight
import org.goalteam.tunelint.view.musicsheet.viewable.RestViewable
import org.goalteam.tunelint.view.musicsheet.viewable.origin
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.eighth_rest
import tunelint.composeapp.generated.resources.quarterrest
import tunelint.composeapp.generated.resources.rect_rest

@Composable
fun BoxScope.QuarterRest(
    geometryData: InternalGeometryData,
    highlight: Modifier,
) {
    Image(
        painter = painterResource(Res.drawable.quarterrest),
        contentDescription = null,
        modifier =
            Modifier
                .offset(y = geometryData.topMargin)
                .size(height = geometryData.staffHeight, width = geometryData.horizontalStep * 2)
                .align(Alignment.TopCenter)
                .then(highlight),
    )
}

@Composable
fun BoxScope.EighthRest(
    geometryData: InternalGeometryData,
    highlight: Modifier,
) {
    Image(
        painter = painterResource(Res.drawable.eighth_rest),
        contentDescription = null,
        modifier =
            Modifier
                .offset(y = geometryData.topMargin)
                .size(height = geometryData.staffHeight, width = geometryData.horizontalStep * 2)
                .align(Alignment.TopCenter)
                .then(highlight),
    )
}

@Composable
fun BoxScope.HalfRest(
    rest: RestViewable,
    geometryData: InternalGeometryData,
    highlight: Modifier,
) {
    Box(
        modifier =
            Modifier
                .size(width = 1.5 * geometryData.verticalStep, height = 0.5 * geometryData.verticalStep)
                .offset(
                    x = rest.stepsBeforeMiddle() * geometryData.horizontalStep - geometryData.verticalStep,
                    y = geometryData.topMargin + 2.5 * geometryData.verticalStep,
                ).then(highlight),
    ) {
        Image(
            painter = painterResource(Res.drawable.rect_rest),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
fun BoxScope.WholeRest(
    rest: RestViewable,
    geometryData: InternalGeometryData,
    highlight: Modifier,
) {
    Box(
        modifier =
            Modifier
                .size(width = 1.5 * geometryData.verticalStep, height = 0.5 * geometryData.verticalStep)
                .offset(
                    x = rest.stepsBeforeMiddle() * geometryData.horizontalStep - geometryData.verticalStep,
                    y = geometryData.topMargin + 2 * geometryData.verticalStep,
                ).then(highlight),
    ) {
        Image(
            painter = painterResource(Res.drawable.rect_rest),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
fun RestView(
    vm: RedactorScreenViewModel,
    position: Int,
    measure: Int,
    rest: RestViewable,
    geometryData: InternalGeometryData,
) {
    InteractableBox(
        vm,
        geometryData,
        rest.stepsBeforeMiddle(),
        rest.stepsAfterMiddle(),
        position,
        measure,
    ) {
        val highlight = vm.styles.value.find(rest.origin())
        when (rest.primaryValue()) {
            PrimaryNoteValue.Whole -> WholeRest(rest, geometryData, highlight)
            PrimaryNoteValue.Half -> HalfRest(rest, geometryData, highlight)
            PrimaryNoteValue.Quarter -> QuarterRest(geometryData, highlight)
            PrimaryNoteValue.Eighth -> EighthRest(geometryData, highlight)
        }
    }
}
