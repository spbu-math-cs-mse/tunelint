package org.goalteam.tunelint.view.musicsheet

import androidx.compose.ui.unit.dp

// TODO: remove magic constants, deriving from the amount of staff lines

class GeometryData(
    vStep: Int,
    hStep: Int,
    upperMargin: Int,
    lowerMargin: Int,
) {
    val verticalStep = vStep.dp

    val horizontalStep = hStep.dp

    val topMargin = upperMargin.dp

    val bottomMargin = lowerMargin.dp

    val staffHeight = (vStep * 5).dp

    val staffShortHeight = (vStep * 4).dp

    val firstLineOffset = topMargin + staffHeight

    val fullHeight = topMargin + bottomMargin + staffHeight
}
