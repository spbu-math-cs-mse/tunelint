package org.goalteam.tunelint.view.toolbar

import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
internal fun undoRedoButtonColors() =
    ButtonDefaults.buttonColors(
        backgroundColor = Color.White,
        disabledBackgroundColor = Color.White,
        contentColor = Color.Black,
        disabledContentColor = Color.LightGray,
    )

@Composable
internal fun editButtonElevation() =
    ButtonDefaults.elevation(
        defaultElevation = 0.dp,
        pressedElevation = 0.dp,
        disabledElevation = 0.dp,
        hoveredElevation = 0.dp,
        focusedElevation = 0.dp,
    )
