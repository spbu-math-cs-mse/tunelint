package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.jetbrains.compose.resources.painterResource
import tunelint.composeapp.generated.resources.Res
import tunelint.composeapp.generated.resources.lint

@OptIn(ExperimentalFoundationApi::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun LintButton(sheet: MusicSheet) {
    val show = remember { mutableStateOf(false) }
    val tipDelay = 750
    TooltipArea(
        tooltip = {
            Card(shape = CutCornerShape(3.dp)) {
                Box(modifier = Modifier.background(Color.LightGray)) {
                    Text(text = "Lint", modifier = Modifier.padding(4.dp))
                }
            }
        },
        tooltipPlacement = TooltipPlacement.ComponentRect(),
        delayMillis = tipDelay,
    ) {
        Button(
            onClick = {
                show.value = true
            },
            colors =
            ButtonDefaults.textButtonColors(
                disabledContentColor = Color.Transparent,
                contentColor = Color.Black,
            ),
            shape = CutCornerShape(0.dp),
            elevation =
            ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp,
                hoveredElevation = 0.dp,
                focusedElevation = 0.dp,
            ),
            modifier = Modifier.padding(0.dp, 8.dp),
        ) {
            Icon(
                painter = painterResource(Res.drawable.lint),
                contentDescription = "lint",
                modifier = Modifier.size(28.dp),
            )
        }
    }
    if (show.value) {
        LintDialog(sheet, hide = { show.value = false })
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun LintDialog(
    sheet: MusicSheet,
    hide: () -> Unit,
) = Dialog(
    properties = DialogProperties(usePlatformDefaultWidth = false),
    onDismissRequest = hide
) {
    Text(
        color = Color.Red,
        text = "your sheet is shit:\n" + melody(sheet).timeSignature.toString(),
        modifier = Modifier.background(Color.White).padding(5.dp),
    )
}

private fun melody(sheet: MusicSheet) =
    sheet
        .contents()
