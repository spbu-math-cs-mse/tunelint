package org.goalteam.tunelint.view.menu

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun MenuColors() =
    ButtonDefaults.textButtonColors(
        disabledContentColor = Color.Transparent,
        contentColor = Color.LightGray,
    )

internal fun menuButtonShape() = CutCornerShape(0.dp)

@Composable
internal fun menuButtonElevation() =
    ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 1.dp,
        disabledElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 1.dp,
    )
