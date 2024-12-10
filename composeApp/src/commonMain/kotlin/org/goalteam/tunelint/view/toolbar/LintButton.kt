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
    Area(show)
    if (show.value) {
        LintDialog(
            statuses(rules.value),
            hide = {
                rules.value = Rules(sheet.contents())
                show.value = false
            },
        )
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
private fun Area(show: MutableState<Boolean>) {
    StyledTooltipArea(hint = "Lint") {
        StyledButton(
            onClick = {
                show.value = true
            },
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
