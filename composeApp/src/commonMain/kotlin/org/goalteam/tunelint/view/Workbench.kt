package org.goalteam.tunelint.view

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import org.goalteam.tunelint.lint.status.Status
import org.goalteam.tunelint.view.lint.LintView
import org.goalteam.tunelint.view.musicsheet.MusicSheetView
import org.goalteam.tunelint.view.musicsheet.SheetStyles
import org.goalteam.tunelint.viewmodel.RedactorScreenViewModel
import java.util.IdentityHashMap

@Suppress("ktlint:standard:function-naming")
@Composable
fun Workbench(
    vm: MutableState<RedactorScreenViewModel>,
    lint: MutableState<List<Status>>,
) = BoxWithConstraints {
    val density = LocalDensity.current
    val height = with(density) { constraints.maxHeight.toDp() }
    val width = with(density) { constraints.maxWidth.toDp() }
    val thickness = 5.dp
    var divider: Dp by remember { mutableStateOf(height * 0.618f) }
    Box(
        modifier =
            Modifier
                .requiredHeight(
                    if (lint.value.isEmpty()) {
                        height
                    } else {
                        divider
                    },
                ).requiredWidth(width),
    ) {
        MusicSheetView(vm.value)
    }
    if (lint.value.isNotEmpty()) {
        Column {
            Spacer(modifier = Modifier.height(divider))
            Box(
                Modifier
                    .height(thickness)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .clip(RoundedCornerShape(1.dp))
                    .shadow(5.dp)
                    .draggable(
                        orientation = Orientation.Vertical,
                        state =
                            rememberDraggableState { delta ->
                                val shifted = divider + with(density) { delta.toDp() }
                                divider = max(min(shifted, height - 100.dp), 200.dp)
                            },
                        startDragImmediately = true,
                    ).background(Color.LightGray),
            )
            LintView(lint, height - divider - thickness) { status, color ->
                vm.value.styles.value =
                    SheetStyles(
                        status
                            .at()
                            .zip(
                                status.at().map { Modifier.background(color.copy(alpha = 0.5f)) },
                            ).toMap(IdentityHashMap()),
                    )
            }
        }
    }
}
