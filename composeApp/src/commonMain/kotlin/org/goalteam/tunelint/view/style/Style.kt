package org.goalteam.tunelint.view.style

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun colors() =
    ButtonDefaults.textButtonColors(
        disabledContentColor = Color.Transparent,
        contentColor = Color.LightGray,
    )

@Composable
internal fun selectableButtonColors() =
    ButtonDefaults.buttonColors(
        backgroundColor = Color.White,
        disabledBackgroundColor = Color.LightGray,
        contentColor = Color.Black,
        disabledContentColor = Color.Black,
    )

internal fun buttonShape() = CutCornerShape(0.dp)

@Composable
internal fun buttonElevation() =
    ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp,
    )
