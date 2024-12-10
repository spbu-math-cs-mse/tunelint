package org.goalteam.tunelint.view.menu

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyShortcut
import org.goalteam.tunelint.musicsheet.MusicSheet
import org.goalteam.tunelint.view.style.StyledButton

@Suppress("ktlint:standard:function-naming")
@Composable
internal fun NewButton(handle: (MusicSheet) -> Unit) {
    val show = remember { mutableStateOf(false) }
    StyledButton(
        onClick = {
            show.value = true
        },
    ) {
        Text("New", color = Color.Black)
        KeyShortcut(Key.N, ctrl = true)
    }
    if (show.value) {
        SaveAsDialog(hide = { show.value = false }) {
            val sheet = MusicSheet(it)
            sheet.save(listOf())
            handle(sheet)
        }
    }
}
