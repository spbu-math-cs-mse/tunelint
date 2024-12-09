package org.goalteam.tunelint.view.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.goalteam.tunelint.musicsheet.MusicSheet

@Suppress("ktlint:standard:function-naming")
@Composable
fun LintButton(sheet: MusicSheet) {
    val show = remember { mutableStateOf(false) }
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
        Text("Lint", color = Color.Black)
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
) = Dialog(properties = DialogProperties(usePlatformDefaultWidth = false), onDismissRequest = hide) {
    Text(
        color = Color.Red,
        text = "your sheet is shit:\n" + melody(sheet).timeSignature.toString(),
        modifier = Modifier.background(Color.White).padding(5.dp),
    )
}

private fun melody(sheet: MusicSheet) =
    sheet
        .contents()
