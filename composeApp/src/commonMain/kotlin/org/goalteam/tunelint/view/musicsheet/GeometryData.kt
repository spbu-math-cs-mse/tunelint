package org.goalteam.tunelint.view.musicsheet

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlin.math.floor

object Constants {
    const val STAFF_LINES = 5
}

interface ExternalGeometryData {
    val verticalStep: Dp
    val topMargin: Dp
    val bottomMargin: Dp
    val fullWidth: Dp
    val leftMargin: Dp
    val rightMargin: Dp
}

val ExternalGeometryData.firstLineOffset get() = topMargin + staffHeight
val ExternalGeometryData.staffHeight get() = Constants.STAFF_LINES * verticalStep
val ExternalGeometryData.staffShortHeight get() = (Constants.STAFF_LINES - 1) * verticalStep
val ExternalGeometryData.fullHeight get() = topMargin + staffHeight + bottomMargin
val ExternalGeometryData.staffWidth get() = fullWidth - leftMargin - rightMargin

interface ExternalEvaluatableGeometryData : ExternalGeometryData {
    val maxSteps: Int

    fun evaluated(horizontalSteps: Int): InternalGeometryData
}

interface InternalGeometryData : ExternalGeometryData {
    val horizontalStep: Dp
}

class ExternalEvaluatableGeometryDataImpl(
    verticalStep: Double,
    minimalHorizontalStep: Double,
    upperMargin: Double,
    lowerMargin: Double,
    fullWidth: Double,
    leftMargin: Double,
    rightMargin: Double,
) : ExternalEvaluatableGeometryData {
    override val verticalStep = verticalStep.dp
    override val topMargin = upperMargin.dp
    override val bottomMargin = lowerMargin.dp
    override val fullWidth = fullWidth.dp
    override val leftMargin = leftMargin.dp
    override val rightMargin = rightMargin.dp

    override val maxSteps = floor(staffWidth / minimalHorizontalStep.dp).toInt()

    override fun evaluated(horizontalSteps: Int): InternalGeometryData {
        require(horizontalSteps <= maxSteps)
        val horizontalStep = staffWidth / horizontalSteps

        return InternalGeometryDataImpl(this, horizontalStep)
    }
}

class InternalGeometryDataImpl(
    external: ExternalGeometryData,
    override val horizontalStep: Dp,
) : InternalGeometryData,
    ExternalGeometryData by external
