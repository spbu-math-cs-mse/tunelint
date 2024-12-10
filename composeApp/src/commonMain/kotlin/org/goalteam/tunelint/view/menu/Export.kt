package org.goalteam.tunelint.view.menu

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import org.goalteam.tunelint.filesupport.ParserProperty
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.property.PathProperty
import org.goalteam.tunelint.view.style.StyledButton

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun ExportButton(
    format: ParserProperty,
    sheet: () -> MusicSheet,
) {
    val show = remember { mutableStateOf(false) }
    StyledButton(
        onClick = {
            show.value = true
        },
    ) {
        Text("Export", color = Color.Black)
        KeyShortcut(Key.S, ctrl = true, shift = true)
    }
    if (show.value) {
        SaveAsDialog(hide = { show.value = false }) {
            sheet().save(listOf(format, PathProperty(it)))
        }
    }
}
